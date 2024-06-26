package rw.ac.rca.OnlineShop.DTOs;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.UUID;

@Data
public class CreateStudentDTO {
    @NotBlank
    private String fullNames;
    private UUID[] courses;
    private String grade;
//    private
}
