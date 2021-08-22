package in.timesinternet.punjiup.dto;

import in.timesinternet.punjiup.entity.embeddable.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String email;
    private String cusPassword;
    private String firstName;
    private String lastName;
    Address address;
}


//{
// "email":"priya@gmail.com",
// "cusPassword":"123",
//"firstName":"priya",
// "lastName":"goenka",
//     "address":{
//         "line1":"asd",
//         "line2":"qwe",
//         "pinCode":"201301",
//         "city":"Noida",
//         "state":"UP"
//
//        }
//
//        }