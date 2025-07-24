package com.cigna.benefit;

import com.cigna.benefit.Service.KafkaBatchProducerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JsonForApplication implements CommandLineRunner {


	private final KafkaBatchProducerService service1;

	public JsonForApplication(KafkaBatchProducerService service1) {
		this.service1 = service1;
	}

	public static void main(String[] args) {
		SpringApplication.run(JsonForApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception{
		service1.sendBatch();
	}
}
