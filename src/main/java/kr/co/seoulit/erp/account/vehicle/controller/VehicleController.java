package kr.co.seoulit.erp.account.vehicle.controller;

import kr.co.seoulit.erp.account.vehicle.servicefacade.VehicleFacadeService;
import kr.co.seoulit.erp.account.vehicle.to.VehicleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;


@CrossOrigin("*")
@RestController
@RequestMapping("/acc/*")
public class VehicleController {

    @Autowired
    private VehicleFacadeService vehicleFacadeService;

    //업무용승용차 전체조회
    @RequestMapping(value ="/vehicle/getVehicleList", method = RequestMethod.GET)
    public HashMap<String,Object> selectVehicleList(){
        HashMap<String,Object> map=new HashMap<>();

        ArrayList<VehicleBean> vehicleList = vehicleFacadeService.selectVehicleList();

        if(vehicleList.size() == 0) {
            map.put("errorMsg","등록된 차량이 없습니다");
            return map;
        }

        map.put("vehicleList", vehicleFacadeService.selectVehicleList());

        return map;
    }
    //업무용승용차 추가
    @RequestMapping(value = "/vehicle/addVehicleList", method = RequestMethod.POST)
    public void insertVehicleList(@RequestBody HashMap<String,ArrayList<VehicleBean>> vehicleList){
        vehicleFacadeService.insertVehicleList(vehicleList);
    }
    //업무용승용차 수정
    @RequestMapping(value = "/vehicle/updateVehicleList", method = RequestMethod.PUT)
    public void updateVehicleList(@RequestBody HashMap<String,ArrayList<VehicleBean>> vehicleList){
        vehicleFacadeService.updateVehicleList(vehicleList);
    }
    //업무용승용차 삭제
    @RequestMapping(value = "/vehicle/deleteVehicleList", method = RequestMethod.DELETE)
    public void deleteVehicleList(@RequestParam("vehicleCode") String vehicleCode){
        vehicleFacadeService.deleteVehicleList(vehicleCode);
    }

}
