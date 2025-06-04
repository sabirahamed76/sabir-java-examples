ELK STACK Example
===================


1) Elasticsearch
1) Go to https://www.elastic.co/downloads/elasticsearch
2) Select an OS based link
3) Extract ZIP file to a location in your system
4) To start it, go to the bin folder and run below command, It will start on port : 9200
> elasticsearch.bat

2) Kibana 
1) Go to https://www.elastic.co/downloads/kibana
2) Select an OS based link
3) Extract ZIP file to a location in your system
4) Link Kibana with Elasticsearch : Open kibana.yml file from config/kibana.yml : uncomment below line
elasticsearch.hosts : http://localhost:9200
5) To start it, go to the bin folder and run below command, It will start on port : 5601
> kibana.bat

3) Logstash 
1) Go to https://www.elastic.co/downloads/logstash
2) Select an OS based link
3) Extract ZIP file to a location in your system
4) Go to bin folder and create one file ‘logstash.conf’ with some configuration. Some examples of this file are given in below link.
https://www.elastic.co/guide/en/logstash/current/config-examples.html
5) To start it, go to the bin folder and run below command
> logstash -f logstash.conf

