package in.timesinternet.punjiup.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(nullable = false, unique = true, updatable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 16)
    private String mgrPassword;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 50)
    private String companyName;
    @Column(nullable = false)
    private String educationQualification;
    @Column(nullable = false)
    private String experience;
    @JsonIgnore
    @OneToMany(mappedBy = "fundManager", cascade = CascadeType.PERSIST)
    List<FundDetails> fundDetailsList = new ArrayList<FundDetails>();
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;


}
