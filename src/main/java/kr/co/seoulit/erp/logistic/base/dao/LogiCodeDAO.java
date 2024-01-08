package kr.co.seoulit.erp.logistic.base.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.erp.logistic.base.to.LogiCodeTO;

@Mapper
public interface LogiCodeDAO {

	public ArrayList<LogiCodeTO> selectCodeList();

	public void insertCode(LogiCodeTO codeTO);

	public void updateCode(LogiCodeTO codeTO);

	public void deleteCode(LogiCodeTO codeTO);

	public String createDivisionCodeNo(String codeType);

}
