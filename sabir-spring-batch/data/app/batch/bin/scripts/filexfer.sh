#!/bin/bash

# Script Name  : filexfer
# Purpose      : to Copy a file from ftp to local and local to ftp
# Date Created : 11-Dec-2019
# Created By   : WIPRO LIMITED

config=$1
key=$2

run_user=`whoami`
#run_user=root

if [ $run_user == 'root' ]; then
	echo Incorrect user, script cannot be run as root user!
	EXIT_CODE=1
	exit $EXIT_CODE
fi

conf_folder=/datacaye/app/batch/ftp/conf
script_folder=/datacaye/app/batch/bin/scripts
log_folder=/datacaye/app/batch/bin/logs/ftp_script
current_time=$(date "+%Y%m")

. ${conf_folder}/${config}.sh

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


SCRIPT_NAME=${0##*/}
SCRIPT_NAME=${SCRIPT_NAME%.*}
SCRIPT_NAME=`echo ${SCRIPT_NAME} | cut -d"-" -f2`

log_file=${log_folder}/$SCRIPT_NAME"_"$current_time.log

echo log file is $log_file

echo "Job $SCRIPT_NAME started at:" `date` >> $log_file

sftp -i /datacaye/app/.ssh/${key} -b ${script_folder}/${SCRIPT_NAME}.ftp $ftpuser@$FTP_host_server 2>&1 >> $log_file | tee -a $log_file

status=${PIPESTATUS[0]}

if [ $status != 0 ]; then
  echo "EXIT_CODE="$status
  echo "EXIT_CODE="$status >> $log_file
  echo "Job $SCRIPT_NAME failed at:" `date` >> $log_file 
  EXIT_CODE=1
  echo "___________________________________________________End of Log for Date: "`date`"___________________________________________________" >>$log_file
  exit $EXIT_CODE  
fi

EXIT_CODE=$?
echo "EXIT_CODE="$EXIT_CODE
echo "EXIT_CODE="$EXIT_CODE >> $log_file

#echo exit $? >> $log_file
#echo "Job $SCRIPT_NAME completed at:" `date` >>$log_file

#exit 0
