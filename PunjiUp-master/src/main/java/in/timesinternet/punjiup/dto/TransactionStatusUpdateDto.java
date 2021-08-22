package in.timesinternet.punjiup.dto;

import in.timesinternet.punjiup.entity.enumaration.TransactionStatus;
import in.timesinternet.punjiup.entity.enumaration.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionStatusUpdateDto {
    Integer transactionId;
    TransactionStatus transactionStatus;
    TransactionType transactionType;
    Double amount;
    Integer fundId;
    Integer customerId;
}


//
//     {
//             "transactionType":"Buy",
//             "transactionStatus":"Pending",
//             "amount":"1525.0",
//             "fundId":"39",
//             "customerId":"24"
//
//             }