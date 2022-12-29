package com.chay.udemy.kafka.order_consumer.customdeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class OrderConsumer2 {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.deserializer", StringDeserializer.class.getName());
		props.setProperty("value.deserializer", OrderDeserializer.class.getName());
		props.setProperty("group.id", "OrderGroup");
		
		KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(props);
//		the name of the topic in this project is OrderCustomTopic
		consumer.subscribe(Collections.singletonList("OrderCustomTopic"));

		ConsumerRecords<String, Order> records = consumer.poll(Duration.ofSeconds(20));
			for (ConsumerRecord<String, Order> record : records) {
				String customerName = record.key();
			Order order = record.value();
			System.out.println("Customer Name: " + customerName);
			System.out.println("Product: " + order.getProduct());
			System.out.println("Quantity: " + order.getQuantity());
		}
		consumer.close();
		}
}
