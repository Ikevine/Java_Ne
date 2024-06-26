package rw.ac.rca.OnlineShop.services;

import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import rw.ac.rca.OnlineShop.DTOs.SaveOrWithdrawMoneyDTO;
import rw.ac.rca.OnlineShop.Enumerations.EBankingRecordType;
import rw.ac.rca.OnlineShop.events.TransactionCompleteEvent;
import rw.ac.rca.OnlineShop.mailHandling.EmailHandlerService;
import rw.ac.rca.OnlineShop.models.Account;
import rw.ac.rca.OnlineShop.models.BankingRecord;
import rw.ac.rca.OnlineShop.models.Customer;
import rw.ac.rca.OnlineShop.repositories.IAccountRepository;
import rw.ac.rca.OnlineShop.repositories.IBankingRepository;
import rw.ac.rca.OnlineShop.repositories.ICustomerRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class BankingService implements IBankingService{

    private final IBankingRepository bankingRepository;
    private final ICustomerRepository customerRepository;
    private final IAccountRepository accountRepository;
    private final EmailHandlerService emailHandlerService;
    private ApplicationEventPublisher eventPublisher;
    @Override
    public BankingRecord newRecord(SaveOrWithdrawMoneyDTO saveOrWithdrawMoneyDTO) throws Exception {
        Customer customer = customerRepository.findById(saveOrWithdrawMoneyDTO.getCustomer_id()).orElseThrow(() -> new BadRequestException("User with given id doesn't exist"));
        if(Objects.equals(saveOrWithdrawMoneyDTO.getType(), EBankingRecordType.SAVING.toString())){
            System.out.println("Saving...");
            customer.setBalance(customer.getBalance() + saveOrWithdrawMoneyDTO.getAmount());
        }else{
            System.out.println("Withdrawing...");
            if(customer.getBalance() < saveOrWithdrawMoneyDTO.getAmount()){
                throw new BadRequestException("Insufficient balance");
            }
            customer.setBalance(customer.getBalance() - saveOrWithdrawMoneyDTO.getAmount());
        }


        customer.setLastUpdateTime(LocalDate.now());
        customerRepository.save(customer);
        BankingRecord bankingRecord = new BankingRecord();
        bankingRecord.setBankingDateTime(LocalDateTime.now());
        bankingRecord.setType(EBankingRecordType.valueOf(saveOrWithdrawMoneyDTO.getType()));
        bankingRecord.setAmount(saveOrWithdrawMoneyDTO.getAmount());
        bankingRecord.setCustomer(customer);
        Account account = accountRepository.findById(saveOrWithdrawMoneyDTO.getAccount_id()).orElseThrow(() -> new BadRequestException("Account doesn't exist"));
        bankingRecord.setAccount(account);
        bankingRecord.setBankingDateTime(LocalDateTime.now());
        eventPublisher.publishEvent(new TransactionCompleteEvent(customer,bankingRecord));
        return bankingRepository.save(bankingRecord);
    }
}
