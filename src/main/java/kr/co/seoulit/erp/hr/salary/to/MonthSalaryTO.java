package kr.co.seoulit.erp.hr.salary.to;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@IdClass(MonthSalaryPKcombine.class)
@Table(name = "MONTH_SALARY")
public class MonthSalaryTO implements Serializable {
	@Id
	private String empCode;
	@Id
	private String applyYearMonth;
	private String salary, totalExtSal, totalPayment, totalDeduction, realSalary, cost, unusedDaySalary, finalizeStatus,
			deptCode;
//	private ArrayList<MonthDeductionTO> monthDeductionList;
//	private ArrayList<MonthExtSalTO> monthExtSalList;
}
