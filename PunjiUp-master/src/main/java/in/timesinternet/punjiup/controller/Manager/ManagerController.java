package in.timesinternet.punjiup.controller.Manager;
import in.timesinternet.punjiup.dto.*;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.FundManager;
import in.timesinternet.punjiup.entity.Transaction;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.TransactionStatus;
import in.timesinternet.punjiup.repository.FundDetailsRepository;
import in.timesinternet.punjiup.repository.FundManagerRepository;
import in.timesinternet.punjiup.repository.TransactionRepository;
import in.timesinternet.punjiup.service.impl.FundManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/fundmanager")
public class ManagerController {
    @Autowired
    FundDetailsRepository fundDetailsRepository;
    @Autowired
    FundManagerRepository fundManagerRepository;
    @Autowired
    FundManagerServiceImpl fundManagerServiceImpl;
    @Autowired
    TransactionRepository transactionRepository;
    @PostMapping("/login")
    Object loginManager(@RequestBody LoginDto loginDto)
    {
        return null;
    }
    @PostMapping("/signup")
    FundManager createManager(@RequestBody  FundManagerDto fundmanagerDto)
    {
        //Manager Service Call;
        FundManager fundManager=fundManagerServiceImpl.createFundManagerAccount(fundmanagerDto);
        return fundManager;
    }
    @GetMapping("/allfundmanager")
    List<FundManager> fundManagerList()
    {
        return fundManagerServiceImpl.getAllFundManager();
    }

    @PutMapping("")
    FundManager updateManager(@RequestBody FundManagerUpdateDTO fundManagerUpdateDTO)
    {
        //update Mangaer Service Call
        FundManager fundManager=fundManagerServiceImpl.UpdateManager(fundManagerUpdateDTO);
        return  fundManager;
    }
    @PostMapping("/addfund")
    FundDetails addFund(@RequestBody FundDto fundDto)
    {
        //update Mangaer Service Call
        FundDetails fundDetails=fundManagerServiceImpl.addFund(fundDto);
        return fundDetails;
    }
    @PutMapping("/fundUpdate")
    FundDetails fundUpdate(@RequestBody FundUpdateDto fundUpdateDto)
    {
        //update Mangaer Service Call
        FundDetails fundDetails=fundManagerServiceImpl.fundUpdate(fundUpdateDto);
        return fundDetails;
    }
    @GetMapping("/{managerId}/funds")
    List<FundDetails> getAllFund(@PathVariable Integer managerId)
    {
        //Get All funds for perticular manager
        List<FundDetails>fundDetailsList=fundManagerServiceImpl.getAllFund(managerId);
        return fundDetailsList;
    }
    @GetMapping("/{mgrId}/fund/{fundId}")
    Object getFund(@PathVariable Integer fundId, @PathVariable Integer mgrId)
    {
        //update Mangaer Service Call
        FundManager fundManager=fundManagerRepository.getById(mgrId);
        FundDetails fundDetails=fundManagerServiceImpl.getFund(fundId,fundManager);
        return fundDetails;
    }
    @GetMapping("/{mgrId}/funds/{fundType}")
    List<FundDetails >getAllTypeFund(@PathVariable FundType fundType, @PathVariable Integer mgrId)
    {
        //get all open or close end fund;
        List<FundDetails> fundDetailsList=fundManagerServiceImpl.getAllTypeFund(fundType,mgrId);
        return fundDetailsList;
    }
    // To update Transaction Status
    @PutMapping("/fund/updatetransaction")
    Transaction updateTransactionStatus(@RequestBody TransactionStatusUpdateDto transactionDto)
    {
        return fundManagerServiceImpl.updateTransactionStatus(transactionDto);
    }


    // To get all Unapproved Transaction
    @GetMapping("/{managerId}/fund/transactions/{transactionStatus}")
    List<Transaction> getTransaction(@PathVariable Integer managerId,@PathVariable TransactionStatus transactionStatus)
    {
        FundManager fundManager=fundManagerRepository.getById(managerId);
        return transactionRepository.findTransactionByTransactionStatusAndFundDetailsFundManager(transactionStatus,fundManager);
    }
    @GetMapping("{managerId}/transactions")
    List<Transaction>getAllTransaction(@PathVariable Integer managerId)
    {
        return fundManagerServiceImpl.getAllTransaction(managerId);
    }


}
