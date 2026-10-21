package com.expo.controller;

import com.expo.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/apiversion")
public class KafkaProducer {
    @Autowired
    KafkaService kafkaService;

    @PostMapping(value = "/kafka_send")
    public ResponseEntity<?> kafka_send(@RequestBody String msg){

        System.out.println(msg);
        kafkaService.kafka_send(msg);
        return ResponseEntity.status(HttpStatus.OK).body("Send!");

    }





}
