package empapp.dto;

import empapp.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeCommand {

    private String name;

    private List<Address> addresses;
}
