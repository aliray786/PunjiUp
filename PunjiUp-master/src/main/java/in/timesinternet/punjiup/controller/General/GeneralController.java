package in.timesinternet.punjiup.controller.General;
import in.timesinternet.punjiup.entity.enumaration.FundType;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class GeneralController {
    @GetMapping("funds")
    Object getAllFunds()
    {
        //return list of funds;
        return null;
    }
    @GetMapping("funds/{fundType}")
    Object getAllFundsbyType(@PathVariable FundType fundType)
    {
        //return list of fund by Category like open end and close end;
        return null;
    }
    @GetMapping("fund/{fundId}")
    Object getfund(@PathVariable int fundId)
    {
        //return Perticular fund
        return null;
    }
   //Doubt in How We can Implement Search By fund and FundManager



}
