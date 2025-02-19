package microservice.practitioner_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Practitioner {

    private Long id;
    private String firstName;
    private String lastName;
    private String specialty;
    private String email;
    private String phone;

}