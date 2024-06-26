package rw.ac.rca.OnlineShop.DTOs;
import lombok.Data;

import java.util.UUID;

@Data
public class AddMarksDTO {
    private UUID courseId;
    private UUID studentId;
    private Double score;
}
