package in.timesinternet.punjiup.service;

import in.timesinternet.punjiup.dto.*;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.FundManager;
import in.timesinternet.punjiup.entity.Transaction;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FundManagerService {
    FundManager createFundManagerAccount(FundManagerDto fundmanagerDto);
    ResponseEntity login(String email, String password);
    FundManager UpdateManager(FundManagerUpdateDTO fundManagerUpdateDTO);
    FundDetails addFund(FundDto fundDto);
    FundDetails fundUpdate(FundUpdateDto fundUpdateDto);
    List<FundDetails> getAllFund( int managerId);
    FundDetails getFund( int fundId,FundManager fundManager);
    List<FundDetails> getAllTypeFund(FundType fundType, Integer mgr_id);
    Transaction updateTransactionStatus(TransactionStatusUpdateDto transactionStatusUpdateDto);
    List<FundManager>getAllFundManager();
    List<Transaction> getAllTransaction(Integer mgrId);







}
