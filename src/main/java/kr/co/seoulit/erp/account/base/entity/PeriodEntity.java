package kr.co.seoulit.erp.account.base.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERIOD")
@NoArgsConstructor
@Data
public class PeriodEntity {
    @Id
    private String accountPeriodNo;
    private String periodStartDate;
    private String periodEndDate;
    private String workplaceCode;
}
