package in.timesinternet.punjiup.repository;
import in.timesinternet.punjiup.entity.FundManager;
import in.timesinternet.punjiup.entity.Transaction;
import in.timesinternet.punjiup.entity.enumaration.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findTransactionByTransactionStatusAndFundDetailsFundManager(TransactionStatus transactionStatus, FundManager fundManager);
    List<Transaction>findAllByFundDetailsFundManager(FundManager fundManager);
    List<Transaction>findAllByTransactionStatusAndCustomerCustomerId( TransactionStatus transactionStatus,Integer customerId);
}