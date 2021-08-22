package in.timesinternet.punjiup.dto;

import in.timesinternet.punjiup.entity.embeddable.CloseEndFund;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import in.timesinternet.punjiup.entity.enumaration.IsActive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundDto {
    private String fundName;
    private String symbol;
    private Double totalValue;
    private Double nav;
    private Double expenseRatio;
    private Integer preference;
    private FundType fundType;
    private Double exitLoad;
    private Integer mgrId;
    private IsActive isActive;
    private CloseEndFund closeEndFund;
}

//{

//        "fundName":"Large Cap",
//        "symbol":"LG",
//        "totalValue":"19000.0",
//        "nav":"34.0",
//        "expenseRatio":"35.0",
//        "preference":"1",
//        "fundType":"Close",
//        "exitLoad":"0.56",
//        "mgrId":"2",
//        "isActive":"Yes",
//        "closeEndFund":
//        {
//        "bStartDate":"12/Aug/2020",
//        "bEndDate":"20/Aug/2025",
//        "lockInPeriod":"5"
//
//        }
//        }