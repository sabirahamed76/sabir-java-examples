#!/bin/bash

# Script Name  : archive
# Purpose      : to Copy a file from ftp directory into ftp archive directory
# Date Created : 11-Dec-2019
# Created By   : WIPRO LIMITED


### Input parameters
### ==========================================
SRCDIR=${1}
SRCFILE=${2}
BKPDIR=${3}
FREQ=${4}
DATE=`date +%Y%m%d`
TIME=`date +%H%M%S`
current_time=$(date "+%Y%m")

### Block script from being run with root user
### ==========================================
run_user=`whoami`
#run_user=root

if [ $run_user == 'root' ]; then
        echo Incorrect user, script cannot be run as root user!
	EXIT_CODE=1        
	exit $EXIT_CODE
fi

### Get the Script name from the calling script
### ===========================================
SCRIPT_NAME=${0##*/}
SCRIPT_NAME=${SCRIPT_NAME%.*}
SCRIPT_NAME=`echo ${SCRIPT_NAME} | cut -d"-" -f2`

### Get the Module name from the script name
### ========================================
MODULE=`echo ${SCRIPT_NAME} | cut -b1-3 | tr "[A-Z]" "[a-z]"`

script_folder=/datacaye/app/batch/bin/scripts
log_folder=/datacaye/app/batch/bin/logs/ftp_script

if [ ! -d ${log_folder} ]; then
  echo ${log_folder} does not exists. Creating folder...
  mkdir -p ${log_folder}
  if [ $? != 0 ]; then
    echo ${log_folder} creation failed.
    EXIT_CODE=1
    exit $EXIT_CODE
  fi
  echo ${log_folder} successfully created.
fi

#log_file=${log_folder}/$SCRIPT_NAME.log
log_file=${log_file}

echo log file is $log_file

echo "Job $SCRIPT_NAME started at:" `date` >> $log_file

# if file not exist then no need to remove the file
if [ ! -f ${SRCDIR}/${SRCFILE} ]; then
#    echo "EXIT CODE - 131: File or directory is not found ${SRCDIR}/${SRCFILE}. ends at :" `date "+%Y-%m-%d %H:%M:%S"` >> $log_file
    echo exit 131: File or directory is not found ${SRCDIR}/${SRCFILE}. >> $log_file
    echo "Job $SCRIPT_NAME failed at:" `date` >> $log_file
    EXIT_CODE=131
    echo "___________________________________________________End of Log for Date: "`date`"___________________________________________________" >>$log_file
    exit $EXIT_CODE
fi

# if directory permission is not allowed
if [ ! -w ${SRCDIR} ]; then
#    echo "EXIT CODE - 134: Permission denied on source file or directory ${SRCDIR}. ends at :" `date "+%Y-%m-%d %H:%M:%S"` >> $log_file
    echo exit 134: Permission denied on source file or directory ${SRCDIR}. >> $log_file
    echo "Job $SCRIPT_NAME failed at:" `date` >> $log_file
    EXIT_CODE=134
    echo "___________________________________________________End of Log for Date: "`date`"___________________________________________________" >>$log_file
    exit $EXIT_CODE
fi

if [ ! -d ${BKPDIR} ]; then
#    echo "EXIT CODE - 131: Target directory is not found ${BKPDIR}. ends at :" `date "+%Y-%m-%d %H:%M:%S"` >> $log_file
    echo exit 131: Target directory is not found ${BKPDIR}. >> $log_file
    echo "Job $SCRIPT_NAME failed at:" `date` >> $log_file
    EXIT_CODE=131
    echo "___________________________________________________End of Log for Date: "`date`"___________________________________________________" >>$log_file
    exit $EXIT_CODE
fi

if [ ! -w ${BKPDIR} ]; then
#    echo "EXIT CODE - 134: Permission denied on target directory ${BKPDIR}. ends at :" `date "+%Y-%m-%d %H:%M:%S"` >> $log_file
    echo exit 134: Permission denied on target directory ${BKPDIR}. >> $log_file
    echo "Job $SCRIPT_NAME failed at:" `date` >> $log_file
    EXIT_CODE=134
    echo "___________________________________________________End of Log for Date: "`date`"___________________________________________________" >>$log_file
    exit $EXIT_CODE
fi

#Dos2Unix conversion of Source file
dos2unix ${SRCDIR}/${SRCFILE}

# Checking number of records from source file
echo "Source File Record Count: " >> $log_file
wc -l ${SRCDIR}/${SRCFILE} >> $log_file

# copy the file to its backup directory
cp -p ${SRCDIR}/${SRCFILE} ${BKPDIR}/${SRCFILE}.${DATE}.${TIME}_${FREQ} 2>&1 >> $log_file | tee -a $log_file

status=${PIPESTATUS[0]}

if [ $status != 0 ]; then
  echo "EXIT_CODE="$status
  echo "EXIT_CODE="$status >> $log_file
  echo "Job $SCRIPT_NAME failed at:" `date` >> $log_file
  EXIT_CODE=1
  echo "___________________________________________________End of Log for Date: "`date`"___________________________________________________" >>$log_file
  exit $EXIT_CODE
fi

if [ ! -f ${script_folder}/${SCRIPT_NAME}.purg ]; then
   echo "No purge file found"
   # echo "Purge File Not Found at ${SRCDIR}/${SRCFILE}" `date "+%Y-%m-%d %H:%M:%S"` >> $log_file
   # Purging source file from local directory for file tranfer type put
   rm ${SRCDIR}/${SRCFILE} 2>&1 >> $log_file | tee -a $log_file
else
   # Purging sourrce file from ftp upon successful archival of the file for file transfer type get
   sftp -i /datacaye/app/.ssh/${key} -b ${script_folder}/${SCRIPT_NAME}.purg $ftpuser@$FTP_host_server 2>&1 >> $log_file | tee -a $log_file
fi

#echo exit $? >> $log_file
#echo "Job $SCRIPT_NAME completed at:" `date` >>$log_file

EXIT_CODE=$?
echo "EXIT_CODE="$EXIT_CODE
echo "EXIT_CODE="$EXIT_CODE >> $log_file

echo "Job $SCRIPT_NAME completed at:" `date` >> $log_file

#exit 0

echo "___________________________________________________End of Log for Date: "`date`"___________________________________________________" >>$log_file
