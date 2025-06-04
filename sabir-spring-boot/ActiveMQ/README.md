Welcome to Sabir Spring Boot Message ActiveMQ
---------------------------------------------------
Step 1. Download and Install apache-activemq-5.15.16 from below link.

http://activemq.apache.org/components/classic/download/

Step 2. Run the active MQ from the installed below folder

D:\workstation\workspace-java\sabir-spring-boot\sabir-spring-ActiveMQ\apache-activemq-5.16.0\bin\win64

Step 3. Login to ActiveMQ Console using below URL.

http://localhost:8161/admin/

It will ask for username and password. Default username – admin and password – admin.

Once we provide username and password we should able to see below ActiveMQ console

Step 4. Run the AciveMQDemoApplication spring boot

Step 5. Open postman and use below URL to send the POST message.

http://localhost:9091/produce/message

Step 6. Enter the Raw request JSON specified below
{
    "studentId": "20",
    "name": "yahyaa",
    "rollNumber": "0126CS01"
}

Step 7. Our consumer should able to read message. Let’s check the console.

Step 8. If Consumer Listener commented then Message would be in the Queue and can be able to retrieved from Post man be calling the below URL

http://localhost:9091/consume/message

Sample:
https://javatute.com/spring-boot/spring-boot-jms-activemq-producer-and-consumer-example/
https://github.com/netsurfingzone/Spring-Boot-JMS-ActiveMQ-Producer-and-Consumer-Example/tree/master