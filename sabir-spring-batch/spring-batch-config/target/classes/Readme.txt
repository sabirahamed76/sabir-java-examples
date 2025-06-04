-Create folder in D:/data/app/batch/bin/scripts
-Create folder in D:/data/app/batch/bin/logs
-Create folder in D:/data/app/batch/ftp
-Create folder in D:/data/app/batch/ftp/conf
-Create folder in D:/data/app/batch/java
-Create folder in D:/data/app/batch/java/conf
-Create folder in D:/data/app/batch/java/lib
-Create folder in D:/data/app/batch/java/logs
-Create folder in D:/data/appdata/ftp/archive
-Create folder in D:/data/appdata/ftp/sem/y_r_sep_ma_rate
-Create folder in D:/data/appdata/ftp/bank/w_s_sep_info
-Create folder in D:/data/appdata/ftp/bank/w_r_sep_info_ack
-Create folder in D:/data/appdata/ftp/enets/d_r_settlement
-Create folder in D:/data/appdata/ftp/common/a_p_query
-Create folder in D:/data/appdata/ftp/archive/sem/y_r_sep_ma_rate
-Create folder in D:/data/appdata/ftp/archive/bank/w_s_sep_info
-Create folder in D:/data/appdata/ftp/archive/bank/w_r_sep_info_ack
-Create folder in D:/data/appdata/ftp/archive/enets/d_r_settlement
-Create folder in D:/data/appdata/ftp/archive/common/a_p_query


--UNIX
--Test sample Batch Job
sudo -u uxapcye1 java jar -DconfPath=/data/app/batch/java/conf /data/app/batch/java/lib/spring-batch-common.jar --jobName=COMMON_A_P_BatchConfigTest --params > /data/app/batch/java/logs/20210722_COMMON_A_P_BatchConfigTest.log

--Count Records in Table to test Database connectivity
sudo -u uxapcye1 java jar -DconfPath=/data/app/batch/java/conf /data/app/batch/java/lib/spring-batch-common.jar --jobName=COMMON_A_P_Count_Records_Table --params=tableName:sep_info > /data/app/batch/java/logs/20210722_COMMON_A_P_Count_Records_Table.log

--Test Email
sudo -u uxapcye1 java jar -DconfPath=/data/app/batch/java/conf /data/app/batch/java/lib/spring-batch-admin.jar --jobName=COMMON_A_P_Email_Test --params > /data/app/batch/java/logs/20210722_ADMIN_Email_Test.log

--Extract from DB by Query
sudo -u uxapcye1 java jar -DconfPath=/data/app/batch/java/conf /data/app/batch/java/lib/spring-batch-common.jar --jobName=COMMON_A_P_SELECT_QUERY --params > /data/app/batch/java/logs/20210722_COMMON_A_P_QUERY.log



--Extract from DB and load in Stage Table
sudo -u uxapcye1 java jar -DconfPath=/data/app/batch/java/conf /data/app/batch/java/lib/spring-batch-ibis.jar --jobName=IBIS_D_S_SEP_Info_ETL --params > /data/app/batch/java/logs/20210722_Bank_W_S_SEP_Info_ETL.log

--Extract from Stage Table and Create File
sudo -u uxapcye1 java jar -DconfPath=/data/app/batch/java/conf /data/app/batch/java/lib/spring-batch-ibis.jar --jobName=IBIS_D_S_SEP_Info_FileCreate --params > /data/app/batch/java/logs/20210722_Bank_W_S_SEP_Info_FileCreate.log


--Validate incoming File
sudo -u uxapcye1 java jar -DconfPath=/data/app/batch/java/conf /data/app/batch/java/lib/spring-batch-ibis.jar --jobName=COMMON_A_P_SELECT_QUERY --params > /data/app/batch/java/logs/20210722_COMMON_A_P_QUERY.log

--Load incoming File into stage table
sudo -u uxapcye1 java jar -DconfPath=/data/app/batch/java/conf /data/app/batch/java/lib/spring-batch-ibis.jar --jobName=COMMON_A_P_SELECT_QUERY --params > /data/app/batch/java/logs/20210722_COMMON_A_P_QUERY.log

--Process Stage Table Records and insert into transaction tables
sudo -u uxapcye1 java jar -DconfPath=/data/app/batch/java/conf /data/app/batch/java/lib/spring-batch-ibis.jar --jobName=COMMON_A_P_SELECT_QUERY --params > /data/app/batch/java/logs/20210722_COMMON_A_P_QUERY.log



--WINDOWS

java -DconfPath=D:/data/app/batch/java/conf -jar D:/data/app/batch/java/lib/spring-batch-common.jar --jobName=COMMON_A_P_Email_Test --params
java -DconfPath=D:/data/app/batch/java/conf -jar D:/data/app/batch/java/lib/spring-batch-common.jar --jobName=COMMON_A_P_BatchConfigTest --params
java -DconfPath=D:/data/app/batch/java/conf -jar D:/data/app/batch/java/lib/spring-batch-common.jar --jobName=COMMON_A_P_Count_Records_Table --params=tableName:sep_info
java -DconfPath=D:/data/app/batch/java/conf -jar D:/data/app/batch/java/lib/spring-batch-common.jar --jobName=COMMON_A_P_SELECT_QUERY --params


