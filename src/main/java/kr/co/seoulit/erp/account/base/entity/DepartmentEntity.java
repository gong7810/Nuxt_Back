package kr.co.seoulit.erp.account.base.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "DEPARTMENT")
@Data
@IdClass(DepartmentPK.class)
public class DepartmentEntity {
    @Id
    private String workplaceCode;
    @Id
    private String deptCode;
    private String workplaceName;
    private String deptName;
    private String companyCode;
    private String deptStartDate;
    private String deptEndDate;
}
