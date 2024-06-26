package rw.ac.rca.OnlineShop.listeners;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import rw.ac.rca.OnlineShop.events.TransactionCompleteEvent;
import rw.ac.rca.OnlineShop.mailHandling.EmailHandlerService;
import rw.ac.rca.OnlineShop.models.BankingRecord;
import rw.ac.rca.OnlineShop.models.Customer;
import rw.ac.rca.OnlineShop.models.Message;
import rw.ac.rca.OnlineShop.repositories.IMessageRepository;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class TransactionCompleteListener implements ApplicationListener<TransactionCompleteEvent> {
    private final EmailHandlerService emailHandlerService;
    private final IMessageRepository messageRepository;
    @Override
    public void onApplicationEvent(TransactionCompleteEvent event) {
        Customer customer = (Customer) event.getSource();
        BankingRecord bankingRecord = event.getBankingRecord();
        String sentMessage = "Dear " + customer.getFirstName() + " " + customer.getLastName() + " Your " + bankingRecord.getType().toString() + " of " + bankingRecord.getAmount() + " RWF on your " + bankingRecord.getAccount().getNumber() + " has been successfully completed!";
        emailHandlerService.sendMessage(customer.getEmail(), "Transaction Approved!",sentMessage);
        Message message = new Message();
        message.setCustomer_id(customer.getCustomer_id());
        message.setMessage(sentMessage);
        message.setDatetime(LocalDateTime.now());
        messageRepository.save(message);
    }
}