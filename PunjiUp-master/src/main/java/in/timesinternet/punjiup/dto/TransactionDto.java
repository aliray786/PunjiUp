package in.timesinternet.punjiup.dto;
import in.timesinternet.punjiup.entity.enumaration.TransactionStatus;
import in.timesinternet.punjiup.entity.enumaration.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDto {
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