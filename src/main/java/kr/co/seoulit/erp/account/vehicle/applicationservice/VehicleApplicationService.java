package kr.co.seoulit.erp.account.vehicle.applicationservice;

import kr.co.seoulit.erp.account.vehicle.to.VehicleBean;
import kr.co.seoulit.erp.account.vehicle.to.VehicleDetailBean;
import kr.co.seoulit.erp.account.vehicle.to.VehicleLogbookBean;

import java.util.ArrayList;
import java.util.HashMap;

public interface VehicleApplicationService {
    public ArrayList<VehicleBean> selectVehicleList();
    public void insertVehicleList(HashMap<String,ArrayList<VehicleBean>> vehicleList);
    public void updateVehicleList(HashMap<String,ArrayList<VehicleBean>> vehicleList);
    public void deleteVehicleList(String vehicleCode);

    public ArrayList<VehicleDetailBean> selectVehicleDetailList();
    public void updateVehicleDetailList(HashMap<String,ArrayList<VehicleDetailBean>> vehicleDetailList);

    public ArrayList<VehicleLogbookBean> selectVehicleLogbook(String vehicleCode);
    public void insertVehicleLogbook(HashMap<String,ArrayList<VehicleLogbookBean>> vehicleLogbook);
    public void deleteVehicleLogbook(String vehicleCode, String useDate);
}
