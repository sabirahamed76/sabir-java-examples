package com.home.sabir.SpringCloudKafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = "myapp.kafka")
public class KafkaProperties {

	@Bean
	public KafkaProperties kafkaProperties() {
	    return new KafkaProperties();
	}
	
	private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    
}