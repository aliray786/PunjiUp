package in.timesinternet.punjiup.service;
import in.timesinternet.punjiup.dto.*;
import in.timesinternet.punjiup.entity.Customer;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.FundManager;
import in.timesinternet.punjiup.entity.Transaction;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface CustomerService {
    ResponseEntity login(String email, String password);
    Customer createAccount(CustomerDto customerDto);
    List<FundDetails> getAllFunds();
    Transaction startTransaction(TransactionDto transactionDto,String userEmail);
    Customer updateCustomer(InvestorUpdateDto investorUpdateDto, String userEmail);
    FundDetails getFund(int FundId);
    List<FundDetails> getAllTypeFund(FundType fundtype);
    public List<Transaction> showCart(String userEmail);
    public List<FundDetails> getFundsBySearch(String search);
    public String deleteTransaction(DeleteCartItemDto deleteCartItemDto);
    public List<FundManager> getFundManagerBySearch(String search1, String search2);
    public List<Transaction> buyCart(String userEmail);
    Transaction buy( TransactionDto transactionDto,String userEmail);
    Transaction sell( TransactionDto transactionDto,String userEmail);
    public List<FundDetails> getAllFundForManager(Integer fundMgrId);
    Customer showProfile(String email);
    public List<CustomerPositiondto> customerPositionDetail( String email);


}
