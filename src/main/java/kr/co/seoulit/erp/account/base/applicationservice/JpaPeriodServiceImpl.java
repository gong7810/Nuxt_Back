package kr.co.seoulit.erp.account.base.applicationservice;

import kr.co.seoulit.erp.account.base.entity.PeriodEntity;
import kr.co.seoulit.erp.account.base.repository.JpaPeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaPeriodServiceImpl implements JpaPeriodService{
    @Autowired
    JpaPeriodRepository jpaPeriodRepository;

    @Override
    public ArrayList<PeriodEntity> findPeriodNo() {
       ArrayList<PeriodEntity> period = jpaPeriodRepository.findAll();
        return period;
    }
}
