package kr.co.seoulit.erp.account.vehicle.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class VehicleLogbookBean{
    private String vehicleCode;
    private String useDate;
    private String startTime;
    private String empCode;
    private String empName;
    private String deptCode;
    private String deptName;
    private String distance;
    private String businessDistance;
    private String personalDistance;
    private String beforeOdometer;
    private String afterOdometer;
    private String notes;
}