package empapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddressDto {

    @Schema(description = "the id of the address", example = "123")
    private Long id;

    @Schema(description = "the city of the address", example = "Budapest")
    private String city;
}
