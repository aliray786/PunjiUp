package in.timesinternet.punjiup.entity.embeddable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.util.DateHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CloseEndFund {
    @JsonDeserialize(using = DateHandler.class)
    Date bStartDate;
    @JsonDeserialize(using = DateHandler.class)
    Date bEndDate;
    Integer lockInPeriod;

}
