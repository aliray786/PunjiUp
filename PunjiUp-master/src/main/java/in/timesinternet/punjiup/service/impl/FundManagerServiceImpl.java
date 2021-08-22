package in.timesinternet.punjiup.service.impl;
import in.timesinternet.punjiup.dto.*;
import in.timesinternet.punjiup.entity.Customer;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.FundManager;
import in.timesinternet.punjiup.entity.Transaction;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.repository.CustomerRepository;
import in.timesinternet.punjiup.repository.FundDetailsRepository;
import in.timesinternet.punjiup.repository.FundManagerRepository;
import in.timesinternet.punjiup.repository.TransactionRepository;
import in.timesinternet.punjiup.service.FundManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
@Service
public class FundManagerServiceImpl implements FundManagerService {
    @Autowired
    FundManagerRepository fundManagerRepository;
    @Autowired
    FundDetailsRepository fundDetailsRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public FundManager createFundManagerAccount(FundManagerDto fundmanagerDto) {
        FundManager fundManager=new FundManager();
        fundManager.setEmail(fundmanagerDto.getEmail());
        fundManager.setCompanyName(fundmanagerDto.getCompanyName());
        fundManager.setEducationQualification(fundmanagerDto.getEducationQualification());
        fundManager.setExperience(fundmanagerDto.getExperience());
        fundManager.setMgrPassword(fundmanagerDto.getMgrPassword());
        fundManager.setFirstName(fundmanagerDto.getFirstName());
        fundManager.setLastName(fundmanagerDto.getLastName());
        return fundManagerRepository.save(fundManager);
    }

    @Override
    public FundManager UpdateManager(FundManagerUpdateDTO fundManagerUpdateDTO) {
        FundManager fundManager=fundManagerRepository.getById(fundManagerUpdateDTO.getMgrId());
        fundManager.setCompanyName(fundManagerUpdateDTO.getCompanyName());
        fundManager.setEducationQualification(fundManagerUpdateDTO.getEducationQualification());
        fundManager.setExperience(fundManagerUpdateDTO.getExperience());
        fundManager.setFirstName(fundManagerUpdateDTO.getFirstName());
        fundManager.setLastName(fundManagerUpdateDTO.getLastName());
        fundManager.setMgrPassword(fundManagerUpdateDTO.getMgrPassword());
        return fundManagerRepository.save(fundManager);

    }

    @Override
    public FundDetails addFund(FundDto fundDto) {
        FundDetails fundDetails=new FundDetails();
        fundDetails.setFundName(fundDto.getFundName());
        fundDetails.setFundType(fundDto.getFundType());
        fundDetails.setExitLoad(fundDto.getExitLoad());
        fundDetails.setNav(fundDto.getNav());
        fundDetails.setExitLoad(fundDto.getExitLoad());
        fundDetails.setExpenseRatio(fundDto.getExpenseRatio());
        fundDetails.setPreference(fundDto.getPreference());
        fundDetails.setSymbol(fundDto.getSymbol());
        fundDetails.setCloseEndFund(fundDto.getCloseEndFund());
        FundManager fundManager=fundManagerRepository.getById(fundDto.getMgrId());
        fundDetails.setFundManager(fundManager);
        return fundDetailsRepository.save(fundDetails);
    }

    @Override
    public FundDetails fundUpdate(FundUpdateDto fundUpdateDto) {
        FundDetails fundDetails=fundDetailsRepository.getById(fundUpdateDto.getFund_Id());
        fundDetails.setFundType(fundUpdateDto.getFundType());
        fundDetails.setExitLoad(fundUpdateDto.getExitLoad());
        fundDetails.setNav(fundUpdateDto.getNav());
        fundDetails.setExitLoad(fundUpdateDto.getExitLoad());
        fundDetails.setExpenseRatio(fundUpdateDto.getExpenseRatio());
        fundDetails.setPreference(fundUpdateDto.getPreference());
        fundDetails.setCloseEndFund(fundUpdateDto.getCloseEndFund());
        return fundDetailsRepository.save(fundDetails);
    }

    @Override
    public List<FundDetails> getAllFund(int managerId) {
        List<FundDetails> fundDetailsList=fundManagerRepository.getById(managerId).getFundDetailsList();
        return fundDetailsList;
    }

    @Override
    public FundDetails getFund(int fundId,FundManager fundManager) {
        FundDetails fundDetails=fundDetailsRepository.findByFundIdAndFundManager(fundId,fundManager);
        return fundDetails;
    }



    @Override
    public List<FundDetails> getAllTypeFund(FundType fundType, Integer managerId) {
        FundManager fundManager=fundManagerRepository.getById(managerId);
        List<FundDetails> fundDetailsList = fundDetailsRepository.findAllByFundTypeAndFundManager(fundType,fundManager);
        return fundDetailsList;
    }

    @Override
    public Transaction updateTransactionStatus(TransactionStatusUpdateDto transactionStatusUpdateDto) {
        Transaction transaction=transactionRepository.getById(transactionStatusUpdateDto.getTransactionId());
        transaction.setTransactionStatus(transactionStatusUpdateDto.getTransactionStatus());
        FundDetails fundDetails=fundDetailsRepository.getById(transactionStatusUpdateDto.getFundId());
        transaction.setFundDetails(fundDetails);
        transaction.setTransactionType(transactionStatusUpdateDto.getTransactionType());
        transaction.setAmount(transactionStatusUpdateDto.getAmount());
        transaction.setNav(transactionStatusUpdateDto.getAmount());
        Customer customer=customerRepository.getById(transactionStatusUpdateDto.getCustomerId());
        transaction.setCustomer(customer);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<FundManager> getAllFundManager() {
        return fundManagerRepository.findAll();
    }

    /* @Override
     public List<Transaction> transactionList(Integer mgrId, transactionStatus transactionStatus) {
         FundManager fundManager=fundManagerRepository.getById(mgrId);
         List<FundDetails >fundDetails=fundManager.getFundDetailsList();
         List<Transaction>transactionList=fundDetails.
         return transactionList;
     }*/
    @Override
    public List<Transaction> getAllTransaction(Integer mgrId)
    {
        FundManager fundManager=fundManagerRepository.getById(mgrId);
        return transactionRepository.findAllByFundDetailsFundManager(fundManager);
    }

    @Override
    public ResponseEntity login(String email, String password) {
        return null;
    }




}
