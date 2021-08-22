package in.timesinternet.punjiup.service;
import in.timesinternet.punjiup.dto.CartUpdateDto;
import in.timesinternet.punjiup.dto.CustomerDto;
import in.timesinternet.punjiup.dto.InvestorUpdateDto;
import in.timesinternet.punjiup.dto.TransactionDto;
import in.timesinternet.punjiup.entity.Customer;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.FundManager;
import in.timesinternet.punjiup.entity.Transaction;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity login(String email, String password);
    Customer createAccount(CustomerDto customerDto);
    List<FundDetails> getAllFunds();
    Transaction startTransaction(TransactionDto transactionDto);
    Customer updateCustomer(InvestorUpdateDto investorUpdateDto);
    FundDetails getFund(int FundId);
    List<FundDetails> getAllTypeFund(FundType fundtype);
    public List<Transaction> showCart(int customerId);
    public List<FundDetails> getFundsBySearch(String search);
    public String deleteTransaction(CartUpdateDto cartUpdateDto);
    public List<FundManager> getFundManagerBySearch(String search1, String search2);
}
