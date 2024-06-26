package rw.ac.rca.OnlineShop.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;
import rw.ac.rca.OnlineShop.models.BankingRecord;

@Data
public class TransactionCompleteEvent extends ApplicationEvent {

    private final BankingRecord bankingRecord;
    public TransactionCompleteEvent(Object source, BankingRecord record) {
        super(source);
        bankingRecord = record;
    }
}
