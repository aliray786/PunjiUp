package in.timesinternet.punjiup.controller.Manager;

import in.timesinternet.punjiup.dto.FundmanagerDto;
import in.timesinternet.punjiup.entity.FundManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {
    @PostMapping
    FundManager SignUp(@RequestBody FundmanagerDto  fundmanagerDto )
}
