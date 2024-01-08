package kr.co.seoulit.erp.account.vehicle.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class VehicleDetailBean{
    private String vehicleCode;
    private String accountCode;
    private String accountName;
    private String assetCode;
    private String assetName;
    private String acquisitionDate;
    private String disposalDate;
    private String expenseCategory;
    private String insuranceStatus;
    private String customerCode;
    private String insuranceCompany;
    private String insuranceStartPeriod;
    private String insuranceEndPeriod;
    private String leaseType;
    private String leaseStartPeriod;
    private String leaseEndPeriod;
    private String usageType;
}

