package rw.ac.rca.OnlineShop.services;

import rw.ac.rca.OnlineShop.DTOs.SaveOrWithdrawMoneyDTO;
import rw.ac.rca.OnlineShop.models.BankingRecord;

public interface IBankingService {
    public BankingRecord newRecord(SaveOrWithdrawMoneyDTO saveOrWithdrawMoneyDTO) throws Exception;
}
