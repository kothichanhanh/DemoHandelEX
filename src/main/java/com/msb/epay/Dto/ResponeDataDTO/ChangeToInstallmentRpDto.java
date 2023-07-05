package com.msb.epay.Dto.ResponeDataDTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ChangeToInstallmentRpDto {
    private String merId;
    private String trxId;
    private String merTrxId;
    private String resultCd;
    private String resultMsg;
    private String bankId;
    private String termIs;
    private String amount;
    private String merFeeIs;
    private String merchantToken;
}
