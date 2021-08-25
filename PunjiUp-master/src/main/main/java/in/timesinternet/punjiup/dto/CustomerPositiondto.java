package in.timesinternet.punjiup.dto;

import in.timesinternet.punjiup.entity.FundDetails;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerPositiondto {
  String FundName;
  Double shares ;
  Double totalValue;

}
