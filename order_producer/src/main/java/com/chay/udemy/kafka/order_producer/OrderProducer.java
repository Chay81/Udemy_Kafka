package com.chay.udemy.kafka.order_producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class OrderProducer {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");

		KafkaProducer<String, Integer> producer = new KafkaProducer<String, Integer>(props);
//		the name of the topic in this project is OrderTopic
		ProducerRecord<String, Integer> record = new ProducerRecord<>("OrderTopic", "Windows 11", 10);

		try {
			producer.send(record, new OrderCallback());
//			Synchronous call
//			RecordMetadata recordMetadata = producer.send(record).get();
//			System.out.println(recordMetadata.partition());
//			System.out.println(recordMetadata.offset());
//			System.out.println("Message Sent Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}

}
