package in.timesinternet.punjiup.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FundManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mgrId;
    @Column(nullable = false,unique = true,updatable = false,length = 50)
    private String Email;
    @Column(nullable = false,length = 16)
    private String password;

    @Column(nullable = false,length = 50)
    private  String firstName;
    @Column(nullable = false,length = 50)
    private String lastName;
    @Column(nullable = false,length = 50)
    private String CompanyName;
    @Column(nullable = false)
    private String educationQualification;
    @Column(nullable = false)
    private String Experience;
   @OneToMany(mappedBy = "fundManager",cascade =CascadeType.PERSIST)
   List<FundDetails> fundDetailsList=new ArrayList<FundDetails>();
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;




}
