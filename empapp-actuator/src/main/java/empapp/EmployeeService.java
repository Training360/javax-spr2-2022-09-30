package empapp;

import empapp.dto.CreateEmployeeCommand;
import empapp.dto.EmployeeDto;
import empapp.dto.UpdateEmployeeCommand;
import empapp.entity.Employee;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

//@Service
@EmployeesService
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper;

    private MeterRegistry meterRegistry;

    @PostConstruct
    public void initCounter() {
        Counter.builder("employees.created")
                .baseUnit("count")
                .description("Number of the created employees")
                .register(meterRegistry);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        log.info("Create employee");
        log.debug("Create employee with name {}", command.getName());

        meterRegistry.counter("employees.created")
                .increment();

        Employee employee = employeeMapper.toEmployee(command);
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeDto(employee);
    }

    public List<EmployeeDto> listEmployees() {
        return employeeMapper.toEmployeesDto(employeeRepository.findAllWithAddresses());
    }

    public EmployeeDto findEmployeeById(long id) {
        return employeeMapper.toEmployeeDto(employeeRepository.findByIdWithAddresses(id)
                        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id)));
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employeeToModify = employeeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeToModify.setName(command.getName());
        return employeeMapper.toEmployeeDto(employeeToModify);
    }

    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findByIdWithAddresses(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
}
