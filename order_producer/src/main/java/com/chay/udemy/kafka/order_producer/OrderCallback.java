package com.chay.udemy.kafka.order_producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallback implements Callback {

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		
//		Asynchronous call 
		System.out.println(metadata.partition());
		System.out.println(metadata.offset());
		System.out.println("Message Sent Succesfully");
		if(exception!=null) {
			exception.printStackTrace();
		}
	}

}
