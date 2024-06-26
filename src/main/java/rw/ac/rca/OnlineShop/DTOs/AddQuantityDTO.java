package rw.ac.rca.OnlineShop.DTOs;

import lombok.Data;
import rw.ac.rca.OnlineShop.Enumerations.EOperationType;

@Data
public class AddQuantityDTO {
    private int count;
    private String operationType;
}
