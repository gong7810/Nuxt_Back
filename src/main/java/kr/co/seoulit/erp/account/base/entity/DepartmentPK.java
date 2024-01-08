package kr.co.seoulit.erp.account.base.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentPK implements Serializable {
    private String workplaceCode;
    private String deptCode;
}
