package in.timesinternet.punjiup.service.impl;

import in.timesinternet.punjiup.dto.CartUpdateDto;
import in.timesinternet.punjiup.dto.CustomerDto;
import in.timesinternet.punjiup.dto.InvestorUpdateDto;
import in.timesinternet.punjiup.dto.TransactionDto;
import in.timesinternet.punjiup.entity.*;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.TransactionStatus;
import in.timesinternet.punjiup.repository.CustomerRepository;
import in.timesinternet.punjiup.repository.FundDetailsRepository;
import in.timesinternet.punjiup.repository.FundManagerRepository;
import in.timesinternet.punjiup.repository.TransactionRepository;
import in.timesinternet.punjiup.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
        {
            Customer customer = new Customer();
            customer.setEmail(customerDto.getEmail());
            customer.setAddress(customerDto.getAddress());
            customer.setCusPassword(customerDto.getCusPassword());
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            return customerRepository.save(customer);
        }

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
        return transactionRepository.findAllByTransactionStatusAndCustomerCustomerId(TransactionStatus.inCart,customerId);
    }

    @Override
    public String deleteTransaction(CartUpdateDto cartUpdateDto){
         transactionRepository.deleteById(cartUpdateDto.getTransactionId());
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



    public List<CustomerFund> customerPositionDetail(@PathVariable CustomerFund customerFund){
       return null;
    }

}
