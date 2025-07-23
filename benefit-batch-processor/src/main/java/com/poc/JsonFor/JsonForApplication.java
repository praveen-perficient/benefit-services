package com.poc.JsonFor;

import com.poc.JsonFor.Service.KafkaBatchProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JsonForApplication implements CommandLineRunner {

	@Autowired
	private KafkaBatchProducerService service1;


	public static void main(String[] args) {
		SpringApplication.run(JsonForApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception{
		service1.sendBatch();
	}
}
