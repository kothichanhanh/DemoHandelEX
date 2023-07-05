package com.msb.epay.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoInstallmentDTO {
    private String merId;
    private String merTrxId;
    private String resultCd;
    private String resultMsg;
    private String userFee;
    private String merFee;
    private String amount;
    private String merchantToken;
    private String data;
}
