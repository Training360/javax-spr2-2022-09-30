package empapp.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmployeeDto {

    private Long id;

    private String name;

    private List<AddressDto> addresses;

    private LocalDateTime created;
}
