package in.timesinternet.punjiup.controller.Investor;
import in.timesinternet.punjiup.dto.CustomerDto;
import in.timesinternet.punjiup.dto.InvestorUpdateDto;
import in.timesinternet.punjiup.dto.LoginDto;
import in.timesinternet.punjiup.dto.TransactionDto;
import in.timesinternet.punjiup.entity.Customer;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.Transaction;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.service.impl.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/investor")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerServiceImp customerServiceImp;
    @PostMapping("/signup")
    Customer createAccount(@RequestBody CustomerDto customerDto)
    {
        Customer customer=customerServiceImp.createAccount(customerDto);
        return customer;
    }
    @PostMapping("/login")
    Object loginInvestor(@RequestBody LoginDto loginDto)
    {
        return null;
    }
    @PutMapping("")
    Object updateInvestor(@RequestBody InvestorUpdateDto investorUpdateDto)
    {
        //Update detail of users
        return null;
    }
    @GetMapping("/getallfunds")
    List<FundDetails> getAllFunds()
    {
        return customerServiceImp.getAllFunds();
    }
    @GetMapping("fund/{FundId}")
    Object getFund(@PathVariable int FundId )
    {
        //Return perticular Fund;
        return null;
    }
    @GetMapping("funds/{fundType}")
    Object getAllTypeFund(@PathVariable FundType fundType)
    {
        //get all open or close end fund;
        return null;
    }
    @PostMapping("fund/transaction")
    Transaction startTransaction(@RequestBody TransactionDto transactionDto)
    {
        //For Recording Transaction for customer
        return customerServiceImp.startTransaction(transactionDto);

    }


}
