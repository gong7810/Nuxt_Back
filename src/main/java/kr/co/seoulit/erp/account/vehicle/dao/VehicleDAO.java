package kr.co.seoulit.erp.account.vehicle.dao;

import kr.co.seoulit.erp.account.vehicle.to.VehicleBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface VehicleDAO {
    ArrayList<VehicleBean> selectVehicleList();
    void insertVehicleList(VehicleBean vehicleBean);
    void updateVehicleList(VehicleBean vehicleBean);
    void deleteVehicleList(String vehicleCode);
}