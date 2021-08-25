package in.timesinternet.punjiup.controller.Manager;
import in.timesinternet.punjiup.dto.*;
import in.timesinternet.punjiup.entity.FundManager;
import in.timesinternet.punjiup.repository.FundDetailsRepository;
import in.timesinternet.punjiup.repository.FundManagerRepository;
import in.timesinternet.punjiup.repository.TransactionRepository;
import in.timesinternet.punjiup.service.FundManagerService;
import in.timesinternet.punjiup.service.UserService;
import in.timesinternet.punjiup.service.impl.BindingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/api/fundmanager")
@CrossOrigin
public class ManagerController {
    @Autowired
    FundDetailsRepository fundDetailsRepository;
    @Autowired
    FundManagerRepository fundManagerRepository;
    @Autowired
    FundManagerService fundManagerService;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;
    @Autowired
    BindingResultService bindingResultService;
    @PostMapping("/login")
    ResponseEntity<HashMap<String, Object>> loginManager(@RequestBody @Valid  LoginDto loginDto, BindingResult bindingResult)
    {
        bindingResultService.validate(bindingResult);

        return ResponseEntity.ok(userService.login(loginDto.getEmail(),loginDto.getPassword()));
    }
    @PostMapping("/signup")
    ResponseEntity<FundManager> createManager(@RequestBody  @Valid FundmanagerDto fundmanagerDto, BindingResult bindingResult)
    {
        //Manager Service Call;
        bindingResultService.validate(bindingResult);
        FundManager fundManager=fundManagerService.createFundManagerAccount(fundmanagerDto);
        return ResponseEntity.ok(fundManager);
    }
    @GetMapping("/allfundmanager")
    ResponseEntity<List<FundManager> >fundManagerList()
    {
        return ResponseEntity.ok(fundManagerService.getAllFundManager());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_FUNDMANAGAER')")
    ResponseEntity<FundManager >updateManager(@RequestBody @Valid  FundManagerUpdateDTO fundManagerUpdateDTO,HttpServletRequest httpServletRequest,BindingResult bindingResult)
    {
        bindingResultService.validate(bindingResult);
        String userEmail=(String) httpServletRequest.getAttribute("userEmail");
        FundManager fundManager=fundManagerService.UpdateManager(fundManagerUpdateDTO,userEmail);
        return  ResponseEntity.ok(fundManager);
    }
    @GetMapping("/profile")
    ResponseEntity<FundManager> showProfile(HttpServletRequest httpServletRequest)
    {
        String userEmail=(String) httpServletRequest.getAttribute("userEmail");
        return ResponseEntity.ok(fundManagerRepository.findByEmail(userEmail).get());

    }




}
