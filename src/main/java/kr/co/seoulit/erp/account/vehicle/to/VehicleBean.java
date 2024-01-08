package kr.co.seoulit.erp.account.vehicle.to;

import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class VehicleBean{
    private String vehicleCode;
    private String vehicleNumber;
    private String vehicleType;
    private String deptCode;
    private String deptName;
    private String empCode;
    private String empName;
    private String availability;

    @Transient
    private String distance;
    @Transient
    private String businessDistance;
    @Transient
    private String businessUsageRatio;
}

