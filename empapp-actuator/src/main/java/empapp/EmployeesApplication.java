package empapp;

import empapp.entity.A;
import empapp.entity.B;
import empapp.entity.C;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Slf4j
public class EmployeesApplication
{

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}


	@Bean
	public A getA() {
		log.info("Get A {}", this.getClass().getName());
		return new A();
	}

	@Bean
	public B getB() {
		return new B(getA());
	}

	@Bean
	public C getC() {
		return new C(getA());
	}
}

