package com.example.Hyper.Market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ImportResource
@RestController
public class HyperMarketApplication {

	@GetMapping("/")
	public String home(){
				// received this message in aws console
		return "First Deployed Project... !!!";
	}

	public static void main(String[] args) {
		SpringApplication.run(HyperMarketApplication.class, args);
	}

}
