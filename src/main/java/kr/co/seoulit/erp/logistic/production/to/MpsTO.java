package kr.co.seoulit.erp.logistic.production.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MpsTO  extends BaseTO{
	
	private String mpsPlanDate;
	private String mpsNo;
	private String contractDetailNo;
	private String dueDateOfMps;
	private String salesPlanNo;
	private String itemCode;
	private String itemName;
	private String mpsPlanAmount;
	private String mrpApplyStatus;
	private String description;
	private String unitOfMps;
	private String mpsPlanClassification;
	private String scheduledEndDate;
	/*@JsonIgnore
	private String status;
*/
}