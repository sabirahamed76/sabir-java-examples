#!/bin/bash

# Script Name  : housekeep.sh
# Purpose      : Housekeep Job for Source File & Log File Directories
# Date Created : 01-Jan-2020
# Created By   : WIPRO LIMITED

###------------------Exit the Job when executed with root account------------------###
run_user=`whoami`
if [ $run_user == 'root' ]; then
	echo Incorrect user, script cannot be run as root user!
	exit 1
fi

###---------------------Exit the Job when its already running----------------------###
USER_NAME=${LOGNAME}

PROC_CNT=`ps -ef |grep "job.name=${JOB_NAME}" |grep ${USER_NAME}|grep -v grep|wc -l`
echo PROC_CNT=`ps -ef |grep "job.name=${JOB_NAME}" |grep ${USER_NAME}|grep -v grep|wc -l`

if [ ${PROC_CNT} -ne 0 ]; then

     echo "$JOB_NAME is already running"
     EXIT_CODE=1
     echo EXIT_CODE:${EXIT_CODE}
     exit $EXIT_CODE    # should be error code according to batch job error code
fi

###-----------------------------Defining path variables-----------------------------###
script_folder=/datacaye/app/batch/bin/scripts
log_folder=/datacaye/app/batch/bin
archive_folder=/datacaye/appdata/ftp/archive
current_time=$(date "+%Y%m%d%H%M%S")

###---------------------Creating Log File Name with Timestamp----------------------###
SCRIPT_NAME=${0##*/}
SCRIPT_NAME=${SCRIPT_NAME%.*}
SCRIPT_NAME=`echo ${SCRIPT_NAME} | cut -d"-" -f2`

log_file=${log_folder}/logs/ftp_script/$SCRIPT_NAME"_"$current_time.log

echo log file is $log_file

echo "Job $SCRIPT_NAME started at:" `date` >> $log_file

###---------------Creating Compressed logs directory with Timestamp----------------###
echo "Creating Compressed logs directory with Timestamp" `date` >> $log_file
cd $log_folder
echo "Compressed File List:" >> $log_file
tar -zcvf logs.tar.gz logs >> $log_file
chmod 755 logs.tar.gz >> $log_file
mv logs.tar.gz logs.tar.gz_$current_time >> $log_file

###-------------Moving the compressed log File to housekeep directory--------------###
echo "Moving the compressed log File to housekeep directory" `date` >> $log_file 
mv logs.tar.gz_$current_time /datacaye/housekeep/logs/ >> $log_file

###-------------------Removing the log files from log directory--------------------###
echo "Removing the log files from log directory" `date` >> $log_file
cd /datacaye/app/batch/bin/logs/ftp_script >> $log_file
rm *.log >> $log_file
cd /datacaye/app/batch/bin/logs/java_script >> $log_file
rm *.log >> $log_file

###--------------Creating Compressed archive directory with Timestamp--------------###
echo "Creating Compressed archive directory with Timestamp" `date` >> $log_file
cd /datacaye/appdata/ftp/
echo "Compressed File List:" >> $log_file
tar -zcvf archive.tar.gz archive >> $log_file
chmod 755 archive.tar.gz >> $log_file
mv archive.tar.gz archive.tar.gz_$current_time >> $log_file

###-----------Moving the compressed archive File to housekeep directory------------###
echo "Moving the compressed archive File to housekeep directory" `date` >> $log_file
mv archive.tar.gz_$current_time /datacaye/housekeep/data/ >> $log_file

###---------------Removing the archive files from archive directory----------------###
echo "Removing the archive files from archive directory" `date` >> $log_file
cd $archive_folder/iras/ >> $log_file
rm ls -f **/* >> $log_file
cd $archive_folder/cns/ >> $log_file
rm ls -f **/* >> $log_file
cd $archive_folder/nice/ >> $log_file
rm ls -f **/* >> $log_file
cd $archive_folder/rnp/ >> $log_file
rm ls -f **/* >> $log_file
cd $archive_folder/cpr/ >> $log_file
rm ls -f **/* >> $log_file
cd $archive_folder/epr/ >> $log_file
rm ls -f **/* >> $log_file
cd $archive_folder/sem/ >> $log_file
rm ls -f **/* >> $log_file
cd $archive_folder/cpg/ >> $log_file
rm ls -f **/* >> $log_file
cd $archive_folder/ocbc/ >> $log_file
rm ls -f **/* >> $log_file

EXIT_CODE=$?
echo "EXIT_CODE="$EXIT_CODE
#echo EXIT_CODE=$? >> $log_file
echo "EXIT_CODE="$EXIT_CODE >> $log_file
echo "Job $JOB_NAME completed at:" `date` >>$log_file
echo "___________________________________________________End of Log for Date: "`date`"___________________________________________________" >>$log_file
exit $EXIT_CODE
