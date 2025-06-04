package com.home.sabir.SpringCloudKafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.sabir.SpringCloudKafka.component.MessageProducer;
import com.home.sabir.SpringCloudKafka.component.MessageRepository;

@RestController
public class KafkaRestController {

      @Autowired
      private MessageProducer producer;

      @Autowired
      private MessageRepository messageRepo;

      //Send message to kafka
      @GetMapping("/send")
      public String sendMsg(
      @RequestParam("msg") String message) {
          producer.sendMessage(message);
          return message + " sent successfully!";
      }

      //Read all messages
      @GetMapping("/getAll")
      public String getAllMessages() {
         return messageRepo.getAllMessages() ;
      }
}