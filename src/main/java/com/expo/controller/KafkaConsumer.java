package com.expo.controller;

import org.apache.kafka.common.internals.Topic;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "grp_1",groupId = "grp_1")
    public void consumingData(String msg){
        System.out.println(msg+"consumer");
    }
}
