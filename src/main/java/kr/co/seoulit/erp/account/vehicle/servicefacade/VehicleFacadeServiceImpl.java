package kr.co.seoulit.erp.account.vehicle.servicefacade;

import kr.co.seoulit.erp.account.vehicle.applicationservice.VehicleApplicationService;
import kr.co.seoulit.erp.account.vehicle.to.VehicleBean;
import kr.co.seoulit.erp.account.vehicle.to.VehicleDetailBean;
import kr.co.seoulit.erp.account.vehicle.to.VehicleLogbookBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class VehicleFacadeServiceImpl implements VehicleFacadeService{

    @Autowired
    VehicleApplicationService vehicleApplicationService;

    //업무용승용차
    @Override
    public ArrayList<VehicleBean> selectVehicleList(){
        return vehicleApplicationService.selectVehicleList();
    };
    @Override
    public void insertVehicleList(HashMap<String,ArrayList<VehicleBean>> vehicleList){
        vehicleApplicationService.insertVehicleList(vehicleList);
    };
    @Override
    public void updateVehicleList(HashMap<String,ArrayList<VehicleBean>> vehicleList){
        vehicleApplicationService.updateVehicleList(vehicleList);
    };
    @Override
    public void deleteVehicleList(String vehicleCode){
        vehicleApplicationService.deleteVehicleList(vehicleCode);
    };

    //업무용승용차 상세정보 조회
    public ArrayList<VehicleDetailBean> selectVehicleDetailList(){
        return vehicleApplicationService.selectVehicleDetailList();
    };
    //업무용승용차 상세정보 삭제
    public void updateVehicleDetailList(HashMap<String,ArrayList<VehicleDetailBean>> vehicleDetailList){
        vehicleApplicationService.updateVehicleDetailList(vehicleDetailList);
    };

    //운행기록부 전체조회
    public ArrayList<VehicleLogbookBean> selectVehicleLogbook(String vehicleCode) {
        return vehicleApplicationService.selectVehicleLogbook(vehicleCode);
    };
    //운행기록부 추가
    public void insertVehicleLogbook(HashMap<String,ArrayList<VehicleLogbookBean>> vehicleLogbook){
        vehicleApplicationService.insertVehicleLogbook(vehicleLogbook);
    };
    //운행기록부 삭제
    public void deleteVehicleLogbook(String vehicleCode, String useDate){
        vehicleApplicationService.deleteVehicleLogbook(vehicleCode, useDate);
    };
}
