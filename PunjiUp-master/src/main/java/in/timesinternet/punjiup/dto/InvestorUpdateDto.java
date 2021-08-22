package in.timesinternet.punjiup.dto;

import in.timesinternet.punjiup.entity.embeddable.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvestorUpdateDto {
    private Integer customerId;
    private String firstName;
    private String lastName;
    Address address;
}
//{
//"customerId":1,
// "firstName":"Ram",
// "lastName":"Kumar",
// "address":{}
//}