package training.employeeswebclientclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Slf4j
public class EmployeesWebclientClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesWebclientClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting application");
		WebClient client = WebClient.create("http://localhost:8080/api/employees");

//		var json = client
//				.get()
//				.exchangeToMono(response -> response.bodyToMono(String.class))
//				.block();
//		System.out.println(json);

		WebClient.create("http://localhost:8080/api/employees")
				.get()
				.exchangeToFlux(response -> response.bodyToFlux(Employee.class))
				.toStream().forEach(e -> System.out.println(e.getName()));

//		var i = (Function<String, String>) a -> a;


//		System.out.println(employees.getName());
	}
}
