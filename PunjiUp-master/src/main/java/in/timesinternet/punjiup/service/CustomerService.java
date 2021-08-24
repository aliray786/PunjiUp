package in.timesinternet.punjiup.service;
import in.timesinternet.punjiup.dto.CustomerDto;
import in.timesinternet.punjiup.dto.TransactionDto;
import in.timesinternet.punjiup.entity.Customer;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity login(String email, String password);
    Customer createAccount(CustomerDto customerDto);
    List<FundDetails> getAllFunds();
    Transaction startTransaction(TransactionDto transactionDto);

}
