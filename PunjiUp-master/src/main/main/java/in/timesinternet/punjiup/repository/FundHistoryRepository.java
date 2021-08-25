package in.timesinternet.punjiup.repository;

import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.FundHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FundHistoryRepository extends JpaRepository<FundHistory, Integer> {
     List<FundHistory> findByFundDetails(FundDetails fundDetails);
     FundHistory findByFundDetailsAndAndPriceDate(FundDetails fundDetails, Date pricedate);
}
