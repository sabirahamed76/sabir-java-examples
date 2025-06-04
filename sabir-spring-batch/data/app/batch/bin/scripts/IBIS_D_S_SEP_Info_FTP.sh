#!/bin/bash

# Call FTP wrapper script with 2 parameters
# 1. Config file
# 2. SSH key file

FTP_HOME=/data/app/batch/bin/scripts

#SFTP Connection
. ${FTP_HOME}/filexfer.sh MGFTP_CYEOPR_cfg MGFTP-`hostname`-CYE.key

#Archive File
. ${FTP_HOME}/archive.sh /data/appdata/ftp/bank/w_s_sep_info/ SEPFull.dtl /data/appdata/ftp/archive/bank/w_s_sep_info/ W
