package training.employeesopenfeignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "employees", url = "http://localhost:8080")
public interface EmployeeClient {

    @RequestMapping("/api/employees")
    List<Employee> findAll();
}
