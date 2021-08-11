package in.timesinternet.punjiup.repository;

import in.timesinternet.punjiup.entity.FundManager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FundManagerRepository extends JpaRepository<FundManager,Integer> {
}
