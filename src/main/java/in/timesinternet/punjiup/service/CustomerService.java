package in.timesinternet.punjiup.service;

import in.timesinternet.punjiup.dto.CustomerDto;
import in.timesinternet.punjiup.entity.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity login(String email,String password);
    Customer CreateAccount(CustomerDto customerDto);
}
