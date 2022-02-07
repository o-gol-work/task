package ru.olejkai.task_vsr.services.dbAccessServices;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServices {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketServices(final SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessage(String topicSuffix){
        simpMessagingTemplate.convertAndSend("/topic/"+topicSuffix,"send from ws");
    }
}
