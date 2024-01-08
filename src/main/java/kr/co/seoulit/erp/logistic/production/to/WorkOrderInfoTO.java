package kr.co.seoulit.erp.logistic.production.to;

import lombok.Data;

@Data
public class WorkOrderInfoTO {

	private String workOrderNo;
	private String mrpNo;
	private String mpsNo;
	private String mrpGatheringNo;
	private String itemClassification;
	private String itemCode;
	private String itemName;
	private String unitOfMrp;
	private String requiredAmount;
	private String workSiteCode;
	private String workStieName;
	private String productionProcessCode;
	private String productionProcessName;
	private String inspectionStatus;
	private String productionStatus;
	private String completionStatus;

}
