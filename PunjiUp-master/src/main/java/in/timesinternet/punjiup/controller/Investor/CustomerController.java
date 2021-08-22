package in.timesinternet.punjiup.controller.Investor;
import in.timesinternet.punjiup.dto.*;
import in.timesinternet.punjiup.entity.Customer;
import in.timesinternet.punjiup.entity.CustomerFund;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.Transaction;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.service.impl.CustomerServiceImp;
import in.timesinternet.punjiup.service.impl.FundManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

      @RestController
     @RequestMapping("api/investor")
      public class CustomerController {
          @Autowired
          CustomerServiceImp customerServiceImp;
          @Autowired
          FundManagerServiceImpl fundManagerServiceImpl;

          @PostMapping("/signup")
          Customer createAccount(@RequestBody CustomerDto customerDto) {
              Customer customer = customerServiceImp.createAccount(customerDto);
              return customer;
          }

          @PostMapping("/login")
          Object loginInvestor(@RequestBody LoginDto loginDto) {
              return "login";
          }

          @PutMapping("/update")
          Customer updateInvestor(@RequestBody InvestorUpdateDto investorUpdateDto) {
              //Update detail of users
              return customerServiceImp.updateCustomer(investorUpdateDto);
          }


          @GetMapping("/fund")
          List<FundDetails> getAllFunds() {
              return customerServiceImp.getAllFunds();
          }

          @GetMapping("/fund/{FundId}")
          FundDetails getFund(@PathVariable int FundId) {
              //Return perticular Fund;
              return customerServiceImp.getFund(FundId);
          }

          @GetMapping("/fund/{fundType}")
          List<FundDetails> getAllTypeFund(@PathVariable FundType fundType) {
              //get all open or close end fund;
              return customerServiceImp.getAllTypeFund(fundType);
          }


          @PostMapping("/fund/transaction")
          Transaction startTransaction(@RequestBody TransactionStatusUpdateDto transactionStatusUpdateDto) {
              //For Recording Transaction for customer
              return fundManagerServiceImpl.updateTransactionStatus(transactionStatusUpdateDto);

          }

          @PostMapping("/cart/addItem")
          Transaction addFundCart(@RequestBody TransactionDto transactionDto) {

              return customerServiceImp.startTransaction(transactionDto);
          }

          @GetMapping("/{customerId}/cart")
          List<Transaction> showCart(@PathVariable int customerId) {
                 System.out.print("inside show cart");
              return customerServiceImp.showCart(customerId);
          }


          @DeleteMapping("/cart/delete")
          String deleteCartItem(@RequestBody DeleteCartItemDto deleteCartItemDto) {
              return customerServiceImp.deleteTransaction(deleteCartItemDto);
          }

          @GetMapping("/")
          Object customerPositionDetail(@PathVariable CustomerFund customerFund) {
              return null;
          }

          @PostMapping("/buycart")
          List<Transaction> buy(@RequestBody BuyDto buyDto){
              return customerServiceImp.buyCart(buyDto);
          }

          @PostMapping("/buy")
          Transaction buy(@RequestBody TransactionDto transactionDto){
              return customerServiceImp.buy(transactionDto);
          }
      }