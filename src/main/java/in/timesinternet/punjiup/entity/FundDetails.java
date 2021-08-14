package in.timesinternet.punjiup.entity;
import in.timesinternet.punjiup.entity.embeddable.CloseEndFund;
import in.timesinternet.punjiup.entity.enumaration.fundType;
import in.timesinternet.punjiup.entity.enumaration.isActive;
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
public class FundDetails {
    @Id
    @GeneratedValue
    private Integer fund_id;
    @Column(nullable = false,unique = true)
    private String FundName;
    @Column(nullable = false,unique = true)
    private String symbol;
    private  Double total_value;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private fundType fundType;
    @Embedded
    private CloseEndFund closeEndFund;
    @Column(nullable = false)
    private  Double NAV;
    @Column(nullable = false)
    private  Double ExpenseRatio;
    @Column(nullable = false)
    private   Integer preference;
    @Enumerated(EnumType.STRING)
    private isActive isActive;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="mgr_id",referencedColumnName = "mgrId")
    private FundManager fundManager;
    @OneToMany(mappedBy = "fundDetails",cascade = CascadeType.PERSIST)
    private List<Transaction>transactionList=new ArrayList<Transaction>();
    @OneToMany(mappedBy = "fundDetails",cascade = CascadeType.ALL)
    private List<FundHistory>fundHistories=new ArrayList<FundHistory>();
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @Column(nullable = false)
    private Double Exit_Load;

}
