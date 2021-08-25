package in.timesinternet.punjiup.dto;

import in.timesinternet.punjiup.entity.enumaration.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionUpdateManagerDTO {
    Integer transactionId;
    TransactionStatus transactionStatus;
}
