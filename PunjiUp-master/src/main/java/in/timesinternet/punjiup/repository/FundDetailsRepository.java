package in.timesinternet.punjiup.repository;

import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.FundManager;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundDetailsRepository extends JpaRepository<FundDetails, Integer> {

  List<FundDetails>  findAllByFundTypeAndFundManager(FundType fundType, FundManager fundManager);
  List<FundDetails> findFundDetailsByFundManagerMgrId(Integer mgrId);
  List<FundDetails>  findAllByFundType(FundType fundType);
  FundDetails findByFundIdAndFundManager(Integer FundId,FundManager fundManager);
  List<FundDetails>  findAllByFundNameLikeIgnoreCase(String fundName);
}
