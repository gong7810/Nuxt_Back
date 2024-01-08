package kr.co.seoulit.erp.hr.salary.to;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class MonthSalaryPKcombine implements Serializable {
	
	private String empCode;
	
	private String applyYearMonth;
}
