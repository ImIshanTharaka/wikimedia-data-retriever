package com.projects.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.projects.handler.WikimediaChangeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_recentchange";    //topic is configured in KafkaTopicConfig

        //to read real time stream data from wikimedia, we use event handler & event source
        EventHandler eventHandler = new WikimediaChangeHandler(kafkaTemplate, topic);   //define what to do when an event is occurred

        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));    //connecting to the event source wikimedia
        EventSource eventSource = builder.build();

        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
