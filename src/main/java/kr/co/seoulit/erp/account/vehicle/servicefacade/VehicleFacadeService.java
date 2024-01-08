package kr.co.seoulit.erp.account.vehicle.servicefacade;

import kr.co.seoulit.erp.account.vehicle.to.VehicleBean;
import kr.co.seoulit.erp.account.vehicle.to.VehicleDetailBean;
import kr.co.seoulit.erp.account.vehicle.to.VehicleLogbookBean;

import java.util.ArrayList;
import java.util.HashMap;

public interface VehicleFacadeService {
    //업무용승용차
    public ArrayList<VehicleBean> selectVehicleList();
    public void insertVehicleList(HashMap<String,ArrayList<VehicleBean>> vehicleList);
    public void updateVehicleList(HashMap<String,ArrayList<VehicleBean>> vehicleList);
    public void deleteVehicleList(String vehicleCode);

    //업무용승용차 상세정보 조회
    public ArrayList<VehicleDetailBean> selectVehicleDetailList();
    //업무용승용차 상세정보 수정
    public void updateVehicleDetailList(HashMap<String,ArrayList<VehicleDetailBean>> vehicleDetailList);

    //업무용승용차 운행기록부 조회
    public ArrayList<VehicleLogbookBean> selectVehicleLogbook(String vehicleCode);
    //업무용승용차 운행기록부 추가
    public void insertVehicleLogbook(HashMap<String,ArrayList<VehicleLogbookBean>> vehicleLogbook);
    //업무용승용차 운행기록부 삭제
    public void deleteVehicleLogbook(String vehicleCode, String useDate);
}
