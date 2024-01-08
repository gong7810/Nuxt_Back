package kr.co.seoulit.erp.account.currentAsset.to;


import java.util.ArrayList;

import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Data
public class CurrentAssetBean {
  private String accountCode;
  private String accountName;
  private String assetCode;
  private String assetName;
  private String progress;
  private String finalizeStatus;
  private String checkStatus;
  private ArrayList<CurrentAssetDetailBean> currentAssetDetailBean;

  @Transient
  private String acquisitionCost;
  @Transient
  private String depreciation;
  @Transient
  private String bookValue;
  @Transient
  private String normalAmortization;
  @Transient
  private String accumulatedAmortization;
  @Transient
  private String bookValueEnd;
  @Transient
  private String acquisitionQuantity;
  @Transient
  private String changeQuantity;
  @Transient
  private String remainQuantity;


}
