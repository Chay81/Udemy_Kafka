package com.chay.udemy.kafka.order_producer.customserializer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class OrderProducer2 {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty("value.serializer", "com.chay.udemy.kafka.order_producer.customserializer.OrderSerializer");

		KafkaProducer<String, Order> producer = new KafkaProducer<String, Order>(props);
		Order order = new Order();
		order.setCustomerName("Chay1");
		order.setProduct("Windows");
		order.setQuantity(8);
		
		ProducerRecord<String, Order> record = new ProducerRecord<>("OrderCustomTopic", order.getCustomerName(), order);
		try {
			producer.send(record);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}

}
