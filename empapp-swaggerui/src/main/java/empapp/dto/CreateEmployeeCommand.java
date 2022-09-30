package empapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CreateEmployeeCommand {

    @Schema(description = "the name of the created employee", example = "John Doe")
    private String name;


    private List<AddressDto> addresses;
}
