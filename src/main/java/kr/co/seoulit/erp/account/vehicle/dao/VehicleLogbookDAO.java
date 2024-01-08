package kr.co.seoulit.erp.account.vehicle.dao;

import kr.co.seoulit.erp.account.vehicle.to.VehicleDetailBean;
import kr.co.seoulit.erp.account.vehicle.to.VehicleLogbookBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface VehicleLogbookDAO {
    ArrayList<VehicleLogbookBean> selectVehicleLogbook(String vehicleCode);
    void insertVehicleLogbook(VehicleLogbookBean vehicleLogbookBean);
    void deleteVehicleLogbook(String vehicleCode, String useDate);
    void deleteVehicleLogbook(HashMap<String, String> param);
    void deleteVehicleLogbookList(String vehicleCode);
}
