package in.timesinternet.punjiup.service.impl;

import in.timesinternet.punjiup.dto.*;
import in.timesinternet.punjiup.entity.*;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.TransactionStatus;
import in.timesinternet.punjiup.exception.UserAlreadyExistException;
import in.timesinternet.punjiup.repository.CustomerRepository;
import in.timesinternet.punjiup.repository.FundDetailsRepository;
import in.timesinternet.punjiup.repository.FundManagerRepository;
import in.timesinternet.punjiup.repository.TransactionRepository;
import in.timesinternet.punjiup.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    FundDetailsRepository fundDetailsRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    FundManagerRepository fundManagerRepository;

    @Override
    public ResponseEntity login(String email, String password) {
        return null;
    }

    @Override
    public Customer createAccount(CustomerDto customerDto) {

            Customer customer = new Customer();
            customer.setEmail(customerDto.getEmail());
            customer.setAddress(customerDto.getAddress());
            customer.setCusPassword(customerDto.getCusPassword());
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            return customerRepository.save(customer);
        }





    @Override
    public List<FundDetails> getAllFunds() {
        List<FundDetails> fundDetailsList =fundDetailsRepository.findAll();
        return fundDetailsList;
    }

    @Override
    public Transaction startTransaction(TransactionDto transactionDto) {
        Transaction transaction=new Transaction();
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAmount(transactionDto.getAmount());
        Customer customer=customerRepository.getById(transactionDto.getCustomerId());
        transaction.setCustomer(customer);
        FundDetails fundDetails=fundDetailsRepository.getById(transactionDto.getFundId());
        transaction.setNav(fundDetails.getNav());
        Double shares=transactionDto.getAmount()/fundDetails.getNav();
        transaction.setTotalShares(shares);
        transaction.setTransactionStatus(TransactionStatus.inCart);
        transaction.setFundDetails(fundDetails);
        return transactionRepository.save(transaction);
    }


    @Override
    public Customer updateCustomer(InvestorUpdateDto investorUpdateDto) {
        Customer customer= customerRepository.getById(investorUpdateDto.getCustomerId());
        customer.setAddress(investorUpdateDto.getAddress());
        customer.setFirstName(investorUpdateDto.getFirstName());
        customer.setLastName(investorUpdateDto.getLastName());
        return  customerRepository.save(customer);
    }

    @Override
    public FundDetails getFund(int FundId) {
        return fundDetailsRepository.getById(FundId);
    }

    @Override
    public List<FundDetails> getAllTypeFund(FundType fundtype) {
        return fundDetailsRepository.findAllByFundType(fundtype);
    }

    @Override
    public List<Transaction> showCart(int customerId){
          Customer customer=customerRepository.getById(customerId);
          System.out.print(customer);
        return transactionRepository.findAllByTransactionStatusAndCustomer(TransactionStatus.inCart,customer);
    }

    @Override
    public String deleteTransaction(DeleteCartItemDto deleteCartItemDto){
         transactionRepository.deleteById(deleteCartItemDto.getTransactionId());
         return "Successfully Deleted";
    }

    @Override
    public List<FundManager> getFundManagerBySearch(String search1, String search2) {
        return fundManagerRepository.findFundManagerByFirstNameLikeOrLastNameLike(search1,search2);
    }

    @Override
    public List<FundDetails> getFundsBySearch(String search){
        return fundDetailsRepository.findAllByFundNameLikeIgnoreCase(search);
    }



    public List<CustomerFund> customerPositionDetail( CustomerFund customerFund){
       return null;
    }

    public List<Transaction> buyCart(BuyDto buyDto ){
        Customer customer=customerRepository.getById(buyDto.getCustomerId());

        List<Transaction> transactionList=transactionRepository.findAllByTransactionStatusAndCustomer(TransactionStatus.inCart,customer);
        for(Transaction t:transactionList){
            t.setTransactionStatus(TransactionStatus.pending);
            Transaction transaction=transactionRepository.save(t);
        }
        return transactionList;

    }

    @Override
    public  Transaction buy(TransactionDto transactionDto){
        Transaction transaction=new Transaction();
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAmount(transactionDto.getAmount());
        Customer customer=customerRepository.getById(transactionDto.getCustomerId());
        transaction.setCustomer(customer);
        FundDetails fundDetails=fundDetailsRepository.getById(transactionDto.getFundId());
        transaction.setNav(fundDetails.getNav());
        Double shares=transactionDto.getAmount()/fundDetails.getNav();
        transaction.setTotalShares(shares);
        transaction.setTransactionStatus(TransactionStatus.pending);
        transaction.setFundDetails(fundDetails);
        return transactionRepository.save(transaction);

    }

}
