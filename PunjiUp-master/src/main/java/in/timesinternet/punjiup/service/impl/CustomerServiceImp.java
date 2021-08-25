package in.timesinternet.punjiup.service.impl;

import in.timesinternet.punjiup.dto.*;
import in.timesinternet.punjiup.dto.DeleteCartItemDto;
import in.timesinternet.punjiup.entity.*;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.Role;
import in.timesinternet.punjiup.entity.enumaration.TransactionStatus;
import in.timesinternet.punjiup.entity.enumaration.TransactionType;
import in.timesinternet.punjiup.exception.NotFoundException;
import in.timesinternet.punjiup.exception.UserAlreadyExistException;
import in.timesinternet.punjiup.repository.CustomerRepository;
import in.timesinternet.punjiup.repository.FundDetailsRepository;
import in.timesinternet.punjiup.repository.FundManagerRepository;
import in.timesinternet.punjiup.repository.TransactionRepository;
import in.timesinternet.punjiup.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity login(String email, String password) {
        return null;
    }

    @Override
    public Customer createAccount(CustomerDto customerDto) {

        Customer customer = new Customer();
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setRole(Role.ROLE_INVESTOR);
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setIsActive(true);
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new UserAlreadyExistException("Customer with email :" + customer.getEmail() + " already exists");
        }

    }



    @Override
    public List<FundDetails> getAllFunds() {
        List<FundDetails> fundDetailsList =fundDetailsRepository.findAll();
        return fundDetailsList;
    }

    @Override
    public Transaction startTransaction(TransactionDto transactionDto,String userEmail) {
        Optional<FundDetails> fundDetailsOptional=fundDetailsRepository.findById(transactionDto.getFundId());
        if(fundDetailsOptional.isPresent()) {
            FundDetails fundDetails = fundDetailsOptional.get();
            Transaction transaction=new Transaction();
            transaction.setAmount(transactionDto.getAmount());
            transaction.setTransactionType(TransactionType.Buy);
            Optional<Customer>customerOptional=customerRepository.findByEmail(userEmail);
            if(customerOptional.isPresent()){
                Customer customer=customerOptional.get();
                transaction.setCustomer(customer);
                transaction.setNav(fundDetails.getNav());
                Double shares = transactionDto.getAmount() / fundDetails.getNav();
                transaction.setTotalShares(shares);
                transaction.setTransactionStatus(TransactionStatus.inCart);
                transaction.setFundDetails(fundDetails);
                return transactionRepository.save(transaction);
            }
            else
                throw  new NotFoundException("Customer  is not found with given email id :"+userEmail);
        }

        else{
            throw new NotFoundException("FundDetail is not found with given id :"+transactionDto.getFundId());
        }

    }


    @Override
    @Transactional
    public Customer updateCustomer(InvestorUpdateDto investorUpdateDto,String userEmail) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(userEmail);
        if (customerOptional.isPresent()) {

            Customer customer = customerOptional.get();
            customer.setAddress(investorUpdateDto.getAddress());
            customer.setFirstName(investorUpdateDto.getFirstName());
            customer.setLastName(investorUpdateDto.getLastName());
            return customerRepository.save(customer);
        } else {
            throw new NotFoundException("Customer is not found with given email :"+userEmail);
        }
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
    public List<Transaction> showCart(String userEmail){
        Optional<Customer> customerOptional=customerRepository.findByEmail(userEmail);
        if(customerOptional.isPresent()){

            Customer customer=customerOptional.get();
            System.out.print(customer);
            return transactionRepository.findAllByTransactionStatusAndCustomer(TransactionStatus.inCart,customer);
        }
        else
            throw  new NotFoundException("Customer  is not found with given email id :"+userEmail);
    }

    @Override
    @Transactional
    public String deleteTransaction(DeleteCartItemDto deleteCartItemDto){
        Optional<Transaction> transactionOptional=transactionRepository.findByTransactionId(deleteCartItemDto.getTransactionId());
        if(transactionOptional.isPresent()){
            Transaction transaction=transactionOptional.get();
            transactionRepository.deleteById(deleteCartItemDto.getTransactionId());
            return "Successfully Deleted";
        }
        else
            throw new NotFoundException(("Transaction is not found with the given id :"+deleteCartItemDto.getTransactionId()));
    }

    @Override
    public List<FundManager> getFundManagerBySearch(String search1, String search2) {
        return fundManagerRepository.findFundManagerByFirstNameContainingOrLastNameContaining(search1,search2);
    }

    @Override
    public List<FundDetails> getFundsBySearch(String search){
        return fundDetailsRepository.findAllByFundNameContainingIgnoreCase(search);
    }



    public List<CustomerFund> customerPositionDetail( CustomerFund customerFund){
        return null;
    }

    @Override
    public List<Transaction> buyCart(BuyDto buyDto,String userEmail ){
        Optional<Customer> customerOptional=customerRepository.findByEmail(userEmail);
        Optional<Customer> customerInDb  = customerRepository.findById(buyDto.getCustomerId());
        if(customerOptional.isPresent()){
            Customer customer=customerOptional.get();
            List<Transaction> transactionList = transactionRepository.findAllByTransactionStatusAndCustomer(TransactionStatus.inCart, customer);
            for (Transaction t : transactionList) {
                t.setTransactionStatus(TransactionStatus.Pending);
                Transaction transaction = transactionRepository.save(t);
            }
            return transactionList;
        }

        else
            throw  new NotFoundException("Customer  is not found with given email id :"+userEmail);
    }




    @Override
    public  Transaction buy(TransactionDto transactionDto,String userEmail){
        Optional<Transaction> transactionOptional=transactionRepository.findByTransactionId(transactionDto.getFundId());
        if(transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            transaction.setTransactionType(transactionDto.getTransactionType());
            transaction.setAmount(transactionDto.getAmount());
            Optional<Customer>customerOptional=customerRepository.findByEmail(userEmail);
            if(customerOptional.isPresent()){
                Customer customer=customerOptional.get();
                transaction.setCustomer(customer);
                FundDetails fundDetails = fundDetailsRepository.getById(transactionDto.getFundId());
                transaction.setNav(fundDetails.getNav());
                Double shares = transactionDto.getAmount() / fundDetails.getNav();
                transaction.setTotalShares(shares);
                transaction.setTransactionStatus(TransactionStatus.inCart);
                transaction.setFundDetails(fundDetails);
                return transactionRepository.save(transaction);
            }
            else
                throw  new NotFoundException("Customer  is not found with given email id :"+userEmail);
        }

        else{
            throw new NotFoundException("Transaction is not found with given id :"+transactionDto.getFundId());
        }

    }

    @Override
    public  Transaction sell (TransactionDto transactionDto,String userEmail){

        return null;

    }


}
