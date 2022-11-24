package empapp;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    // private TransactionTemplate transactionTemplate;

    public Mono<EmployeeDto> createEmployee(Mono<CreateEmployeeCommand> command) {
        return command
                .map(this::toEmployee)
                .flatMap(employeeRepository::save)
                .map(this::toEmployeeDto);
    }

    public Flux<EmployeeDto> listEmployees() {
        ModelMapper modelMapper = new ModelMapper();
        return employeeRepository.findAll().map(e -> modelMapper.map(e, EmployeeDto.class));
    }

    public Mono<EmployeeDto> findEmployeeById(long id) {
        return employeeRepository.findById(id)
                .map(this::toEmployeeDto);
    }

    // Nem megy
    //@Transactional
    public Mono<EmployeeDto> updateEmployee(long id, Mono<UpdateEmployeeCommand> command) {
        return command
                .zipWith(employeeRepository.findById(id))
                .doOnNext(t -> t.getT2().setName(t.getT1().getName()))
                .map(t -> t.getT2())
                .flatMap(e -> employeeRepository.save(e))
                .map(this::toEmployeeDto);
    }


    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDto toEmployeeDto(Employee employee) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Employee toEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(command.getName());
        return employee;
    }
}
