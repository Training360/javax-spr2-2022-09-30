package training.employeesopenfeignclient;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@AllArgsConstructor
public class EmployeesOpenfeignClientApplication implements CommandLineRunner {

	private EmployeeClient employeeClient;

	public static void main(String[] args) {
		SpringApplication.run(EmployeesOpenfeignClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		employeeClient.findAll().forEach(System.out::println);
	}
}
