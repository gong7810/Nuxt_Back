package kr.co.seoulit.erp.account.statement.to;

import kr.co.seoulit.common.to.BaseTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class MonthIncomeStatementBean extends BaseTO{
    private String year;
    private long month;   //String -> long 변경
    private long salesSummary;    //String -> long 변경
    private long salesCostSummary;    //String -> long 변경
    private long grossMargin; //String -> long 변경
    private long salesManageCostSummary;  //String -> long 변경
    private long operatingProfit; //String -> long 변경
    private long nonOperatingProfitSummary;   //String -> long 변경
    private long nonOperatingCostSummary; //String -> long 변경
    private long ordinaryProfit;  //String -> long 변경
    private long corporateTaxSummary; //String -> long 변경
    private long netIncome;   //String -> long 변경

}
