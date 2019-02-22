package com.ps.infrastructure.mq.runner;

import com.ps.infrastructure.mq.msg.model.PseudoMessage;
import com.ps.infrastructure.mq.service.ProducerServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SendMessageRunner implements CommandLineRunner {
    @Autowired
    private ProducerServcie producerServcie;

    public void run(String... args) throws Exception {
        for (int index = 0; index < 10; index++) {
            this.producerServcie.sendMsg("ps.test.queue", String.format("Message with NO.%s", index));
        }
        PseudoMessage pseudoMessage = new PseudoMessage();
        pseudoMessage.setId(1L);
        pseudoMessage.setData("data");
        pseudoMessage.setHint("hint");
        this.producerServcie.sendMsg("ps.test.data.queue",pseudoMessage);
    }
}
