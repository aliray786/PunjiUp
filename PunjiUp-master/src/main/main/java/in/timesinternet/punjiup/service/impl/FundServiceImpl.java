package in.timesinternet.punjiup.service.impl;

import in.timesinternet.punjiup.entity.FundDetails;
import in.timesinternet.punjiup.entity.FundHistory;
import in.timesinternet.punjiup.repository.FundDetailsRepository;
import in.timesinternet.punjiup.repository.FundHistoryRepository;
import in.timesinternet.punjiup.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class FundServiceImpl implements FundService {
    @Autowired
     FundHistoryRepository fundHistoryRepository;
    @Autowired
    FundDetailsRepository fundDetailsRepository;
    @Override
    public Double returnForFund(Integer month, Integer fundId) {
        FundDetails fundDetails = fundDetailsRepository.getById(fundId);
        FundHistory fundHistory= fundHistoryRepository.findByFundDetailsAndAndPriceDate(fundDetails,new Date());
        return null;
    }
}
