package in.timesinternet.punjiup.entity;

import in.timesinternet.punjiup.entity.embeddable.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;
    @Column(nullable = false, unique = true, updatable = false)
    private String Email;
    @Column(nullable = false)
    private String Cus_password;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    @Embedded
    Address address;
    private Date lastTradeDate;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<Transaction> transactionList = new ArrayList<Transaction>();
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CustomerFund> customerFundList = new ArrayList<CustomerFund>();
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;


}
