package kr.co.seoulit.erp.account.vehicle.controller;

import kr.co.seoulit.erp.account.vehicle.servicefacade.VehicleFacadeService;
import kr.co.seoulit.erp.account.vehicle.to.VehicleLogbookBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;


@CrossOrigin("*")
@RestController
@RequestMapping("/acc/*")
public class VehicleLogbookController {

    @Autowired
    private VehicleFacadeService vehicleFacadeService;

    //운행기록부 조회
    @RequestMapping(value ="/vehicle/getVehicleLogbook", method = RequestMethod.GET)
    public HashMap<String,Object> selectVehicleLogbook(@RequestParam("vehicleCode") String vehicleCode){
        HashMap<String,Object> map=new HashMap<>();

        ArrayList<VehicleLogbookBean> vehicleLogbookList = vehicleFacadeService.selectVehicleLogbook(vehicleCode);

        if(vehicleLogbookList.size() == 0) {
            map.put("errorMsg","등록된 운행기록이 없습니다");
            return map;
        }

        map.put("vehicleLogbook", vehicleLogbookList);

        return map;
    }
    //운행기록부 추가
    @RequestMapping(value = "/vehicle/addVehicleLogbook", method = RequestMethod.POST)
    public void insertVehicleLogbook(@RequestBody HashMap<String,ArrayList<VehicleLogbookBean>> VehicleLogbook){
        vehicleFacadeService.insertVehicleLogbook(VehicleLogbook);
    }
    //운행기록부 삭제
    @RequestMapping(value = "/vehicle/deleteVehicleLogbook", method = RequestMethod.DELETE)
    public void deleteVehicleLogbook(@RequestParam("vehicleCode") String vehicleCode,
                                     @RequestParam("useDate") String useDate){
        vehicleFacadeService.deleteVehicleLogbook(vehicleCode, useDate);
    }
}
