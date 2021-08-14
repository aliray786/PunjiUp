package in.timesinternet.punjiup.dto;

import in.timesinternet.punjiup.entity.enumaration.fundType;

import java.util.Date;

public class FundDto {
    private String FundName;
    private String symbol;
    private Double total_value;
    private Integer NAV;
    private Integer Shares;
    private Double ExpenseRatio;
    private Integer preference;
    private fundType fundType;
    private Double exitLoad;
    private Date BendDate;
    private Date BstartDate;
    private int LockinPeriod;
}
