package com.chay.kafka.avro.deserializers;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.chay.kafka.avro.Order;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;

public class OrderConsumer3 {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "http://localhost:9092");
		props.setProperty("key.deserializer", KafkaAvroDeserializer.class.getName());
		props.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
		props.setProperty("group.id", "OrderGroup");
		props.setProperty("schema.registry.url", "http://localhost:8081");
		props.setProperty("specific.avro.reader", "true");
		
		KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(props);
//		the name of the topic in this project is OrderCustomTopic
		consumer.subscribe(Collections.singletonList("OrderAvroTopic"));

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
