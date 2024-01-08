package kr.co.seoulit.erp.account.base.applicationservice;

import kr.co.seoulit.erp.account.base.entity.PeriodEntity;

import java.util.ArrayList;
import java.util.List;

public interface JpaPeriodService {
    ArrayList<PeriodEntity> findPeriodNo();
}
