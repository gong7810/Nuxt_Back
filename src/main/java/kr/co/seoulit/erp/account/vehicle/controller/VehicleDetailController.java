package kr.co.seoulit.erp.account.vehicle.controller;

import kr.co.seoulit.erp.account.vehicle.servicefacade.VehicleFacadeService;
import kr.co.seoulit.erp.account.vehicle.to.VehicleDetailBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;


@CrossOrigin("*")
@RestController
@RequestMapping("/acc/*")
public class VehicleDetailController {

    @Autowired
    private VehicleFacadeService vehicleFacadeService;

    //업무용승용차 상세정보 조회
    @RequestMapping(value ="/vehicle/getVehicleDetailList", method = RequestMethod.GET)
    public HashMap<String,Object> selectVehicleDetailList(){
        HashMap<String,Object> map=new HashMap<>();

        ArrayList<VehicleDetailBean> vehicleDetailList = vehicleFacadeService.selectVehicleDetailList();

        if(vehicleDetailList.size() == 0) {
            map.put("errorMsg","등록된 상세정보가 없습니다");
            return map;
        }

        map.put("vehicleDetailList", vehicleDetailList);
        return map;
    }
    //업무용승용차 상세정보 수정
    @RequestMapping(value = "/vehicle/updateVehicleDetailList", method = RequestMethod.PUT)
    public void updateVehicleDetailList(@RequestBody HashMap<String,ArrayList<VehicleDetailBean>> vehicleDetailList){
        vehicleFacadeService.updateVehicleDetailList(vehicleDetailList);
    }
}