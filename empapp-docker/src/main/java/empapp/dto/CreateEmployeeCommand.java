package empapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CreateEmployeeCommand {

    @NotBlank
    @Schema(description = "the name of the created employee", example = "John Doe")
    private String name;

    private List<AddressDto> addresses;
}
