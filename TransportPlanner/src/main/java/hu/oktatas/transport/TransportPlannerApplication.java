package hu.oktatas.transport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.oktatas.transport.service.InitDbService;

@SpringBootApplication
public class TransportPlannerApplication {
	@Autowired
	InitDbService initDbService;
	
	public static void main(String[] args) {
		SpringApplication.run(TransportPlannerApplication.class, args);
	}

	public void run(String... args) throws Exception {

		initDbService.initDb();
		
	}
}
