package in.timesinternet.punjiup.service;

import in.timesinternet.punjiup.dto.FundmanagerDto;
import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.FundManager;
import org.springframework.http.ResponseEntity;

public interface FundManagerService {
    FundManager createFundManagerAccount(FundmanagerDto  fundmanagerDto);
    ResponseEntity login(String email, String password);


}
