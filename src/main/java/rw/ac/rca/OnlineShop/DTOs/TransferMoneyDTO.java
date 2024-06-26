package rw.ac.rca.OnlineShop.DTOs;

import lombok.Data;

import java.util.UUID;

@Data
public class TransferMoneyDTO {

    private UUID receiver_id;
    private UUID sender_id;
    private double amount;
}
