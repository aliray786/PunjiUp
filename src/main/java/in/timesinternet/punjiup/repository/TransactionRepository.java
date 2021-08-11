package in.timesinternet.punjiup.repository;

import in.timesinternet.punjiup.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
