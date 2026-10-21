package com.expo.serviceimpl;

import com.expo.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    private static final String TOPIC_GRP="grp_1";

    @Override
    public void kafka_send(String msg) {
    System.out.println("Sending"+msg);
    kafkaTemplate.send(TOPIC_GRP,msg+"hi");


    }
}
