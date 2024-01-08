package kr.co.seoulit.erp.account.vehicle.dao;

import kr.co.seoulit.erp.account.vehicle.to.VehicleDetailBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface VehicleDetailDAO {
    ArrayList<VehicleDetailBean> selectVehicleDetailList();
    void insertVehicleDetailList(String vehicleCode);
    void updateVehicleDetailList(VehicleDetailBean vehicleDetailBean);
    void deleteVehicleDetailList(String vehicleCode);
}