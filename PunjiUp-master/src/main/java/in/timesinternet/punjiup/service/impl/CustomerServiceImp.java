package in.timesinternet.punjiup.service.impl;
import in.timesinternet.punjiup.dto.CustomerDto;
import in.timesinternet.punjiup.dto.TransactionDto;
import in.timesinternet.punjiup.entity.Customer;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.Transaction;
import in.timesinternet.punjiup.entity.enumaration.IsActive;
import in.timesinternet.punjiup.entity.enumaration.Role;
import in.timesinternet.punjiup.repository.CustomerRepository;
import in.timesinternet.punjiup.repository.FundDetailsRepository;
import in.timesinternet.punjiup.repository.TransactionRepository;
import in.timesinternet.punjiup.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    FundDetailsRepository fundDetailsRepository;
    @Autowired
    TransactionRepository transactionRepository;

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
            customer.setPassword(customerDto.getPassword());
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setIsActive(true);
            customer.setRole(Role.ROLE_INVESTOR);
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
        transaction.setFundDetails(fundDetails);
        transaction.setNav(fundDetails.getNav());
        transaction.setTransactionStatus(in.timesinternet.punjiup.entity.enumaration.TransactionStatus.Pending);
        Double shares=transactionDto.getAmount()/fundDetails.getNav();
        transaction.setTotalShares(shares);
        return transactionRepository.save(transaction);
    }
}
