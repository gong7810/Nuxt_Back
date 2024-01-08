package kr.co.seoulit.erp.account.vehicle.applicationservice;

import kr.co.seoulit.erp.account.vehicle.dao.VehicleDAO;
import kr.co.seoulit.erp.account.vehicle.dao.VehicleDetailDAO;
import kr.co.seoulit.erp.account.vehicle.dao.VehicleLogbookDAO;
import kr.co.seoulit.erp.account.vehicle.to.VehicleBean;
import kr.co.seoulit.erp.account.vehicle.to.VehicleDetailBean;
import kr.co.seoulit.erp.account.vehicle.to.VehicleLogbookBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class VehicleApplicationServiceImpl implements VehicleApplicationService{

    @Autowired
    private VehicleDAO vehicleDAO;
    @Autowired
    private VehicleDetailDAO vehicleDetailDAO;
    @Autowired
    private VehicleLogbookDAO vehicleLogbookDAO;


    // 업무용승용차 조회
    @Override
    public ArrayList<VehicleBean> selectVehicleList(){
        return vehicleDAO.selectVehicleList();
    };
    @Override
    public void insertVehicleList(HashMap<String,ArrayList<VehicleBean>> vehicleList){

        ArrayList<VehicleBean> addVehicleList = vehicleList.get("vehicleList");
        for (VehicleBean vehicleBean : addVehicleList) {
            vehicleDAO.insertVehicleList(vehicleBean);
        }

        VehicleBean vehicleBean = addVehicleList.get(0);
        String vehicleCode = vehicleBean.getVehicleCode();
        vehicleDetailDAO.insertVehicleDetailList(vehicleCode);
    };

    // 업무용승용차 수정
    @Override
    public void updateVehicleList(HashMap<String,ArrayList<VehicleBean>> vehicleList){

        ArrayList<VehicleBean> updateVehicleList = vehicleList.get("vehicleList");

        for (VehicleBean vehicleBean : updateVehicleList) {
            vehicleDAO.updateVehicleList(vehicleBean);
        }
    };

    // 업무용승용차 삭제
    @Override
    public void deleteVehicleList(String vehicleCode){
        vehicleLogbookDAO.deleteVehicleLogbookList(vehicleCode);
        vehicleDetailDAO.deleteVehicleDetailList(vehicleCode);
        vehicleDAO.deleteVehicleList(vehicleCode);
    };

    //업무용승용차 상세정보 조회
    public ArrayList<VehicleDetailBean> selectVehicleDetailList(){
        return vehicleDetailDAO.selectVehicleDetailList();
    };

    //업무용승용차 상세정보 수정
    public void updateVehicleDetailList(HashMap<String,ArrayList<VehicleDetailBean>> vehicleDetailList){
        ArrayList<VehicleDetailBean> updateVehicleDetailList = vehicleDetailList.get("vehicleDetailList");

        for (VehicleDetailBean vehicleDetailBean : updateVehicleDetailList) {
            vehicleDetailDAO.updateVehicleDetailList(vehicleDetailBean);
        }
    };
 
    //업무용승용차 운행기록부 조회
    public ArrayList<VehicleLogbookBean> selectVehicleLogbook(String vehicleCode){
        return vehicleLogbookDAO.selectVehicleLogbook(vehicleCode);
    }

    //업무용승용차 운행기록부 추가
    public void insertVehicleLogbook(HashMap<String,ArrayList<VehicleLogbookBean>> vehicleLogbook){
        ArrayList<VehicleLogbookBean> addVehicleLogbook = vehicleLogbook.get("vehicleLogbook");

        for (VehicleLogbookBean vehicleLogbookBean : addVehicleLogbook) {
            vehicleLogbookDAO.insertVehicleLogbook(vehicleLogbookBean);
        }

    }
//    //업무용승용차 운행기록부 삭제
//    public void deleteVehicleLogbook(String vehicleCode, String useDate){
//        vehicleLogbookDAO.deleteVehicleLogbook(vehicleCode, useDate);
//    }
    //업무용승용차 운행기록부 삭제
    public void deleteVehicleLogbook(String vehicleCode, String useDate){
        HashMap<String, String> param = new HashMap<>();

        param.put("vehicleCode", vehicleCode);
        param.put("useDate", useDate);

        vehicleLogbookDAO.deleteVehicleLogbook(param);
    }
}
