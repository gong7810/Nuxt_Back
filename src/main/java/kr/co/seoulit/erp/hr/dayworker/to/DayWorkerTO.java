package kr.co.seoulit.erp.hr.dayworker.to;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "NEW_DAY_WORKER")
public class DayWorkerTO extends BaseTO{
	@Id
	private String empCode;				
	
	private String			
				empName,
				joinDate,
				dept,
				phoneNumber,
			    accountNumber,
				accountHolder,
				salary,
				timeSalary;
}
