package kr.co.seoulit.erp.logistic.sales.to;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import kr.co.seoulit.common.to.BaseTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "ESTIMATE_DETAIL")
public class EstimateDetailTO extends BaseTO {

//************************* 2020.08.27 63기 양지훈 수정 시작 *************************
//	description:	React View에서 rowId라는 행 index 값 나타내는 변수;
//
	@Transient
	private String rowId;
//************************* 2020.08.27 63기 양지훈 수정 종료 *************************
	private String unitOfEstimate;
	private String estimateNo;
	private int unitPriceOfEstimate;
	@Id
	private String estimateDetailNo;
	private int sumPriceOfEstimate;
	private String description;
	private String itemCode;
	private int estimateAmount;
	private String dueDateOfEstimate;
	private String itemName;
}