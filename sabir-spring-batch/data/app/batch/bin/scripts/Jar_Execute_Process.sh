#!/bin/bash

# Script Name  : Jar_Execute_Process
# Purpose      : To execute Jar file with input parameters
# Date Created : 11-Dec-2019
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
     echo exit_code : ${EXIT_CODE}
     exit  $EXIT_CODE    # should be error code according to batch job error code
fi

###-----------------------------Defining path variables-----------------------------###
conf_folder=/data/app/batch/ftp/conf
script_folder=/data/app/batch/bin/scripts
log_folder=/data/app/batch/bin/logs/java_script
current_time=$(date "+%Y%m%d%H%M%S")

###--------------------------Defining parameter variables---------------------------###
###-------------------------Third parameter DT is optional--------------------------###
JAR_NAME=$1
JOB_NAME=$2
DT=$3

###-------------------Creating Log Folder when its not available--------------------###
if [ ! -d ${log_folder} ]; then
  echo ${log_folder} does not exists. Creating folder...
  mkdir -p ${log_folder}
  if [ $? != 0 ]; then
    echo ${log_folder} creation failed.
    exit 1
  fi
  echo ${log_folder} successfully created.
fi

###---------------------Creating Log File Name with Timestamp----------------------###
SCRIPT_NAME=${0##*/}
SCRIPT_NAME=${SCRIPT_NAME%.*}
SCRIPT_NAME=`echo ${SCRIPT_NAME} | cut -d"-" -f2`

log_file=${log_folder}/$JOB_NAME"_"$current_time.log

echo log file is $log_file

echo "Job $SCRIPT_NAME started at:" `date` >> $log_file

###--------------------Executing Jar file with three parameters---------------------###
###-------------------------Third parameter DT is optional--------------------------###
if [ $DT != "" ]; then
   java -DconfPath=/data/app/batch/java/conf -jar /data/app/batch/java/lib/$JAR_NAME --jobName=$JOB_NAME --params=$DT >> $log_file #| tee -a $log_file
  else
   java -DconfPath=/data/app/batch/java/conf -jar /data/app/batch/java/lib/$JAR_NAME --jobName=$JOB_NAME --params >> $log_file #| tee -a $log_file
fi

EXIT_CODE=$?
#EXIT_CODE=1
echo "EXIT_CODE="$EXIT_CODE
#echo EXIT_CODE=$? >> $log_file
echo "EXIT_CODE="$EXIT_CODE >> $log_file
echo "Job $JOB_NAME completed at:" `date` >>$log_file
echo "___________________________________________________End of Log for Date: "`date`"___________________________________________________" >>$log_file
exit $EXIT_CODE
#exit 1
