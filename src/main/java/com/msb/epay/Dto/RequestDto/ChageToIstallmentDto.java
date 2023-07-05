package com.msb.epay.Dto.RequestDto;

import com.msb.epay.common.*;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChageToIstallmentDto {
    @NotNull
    private String merId;
    private String merchantToken;
    @NotNull
    private String timeStamp;
    @NotNull
    private String merTrxId;
    @NotNull
    private String trxDt;
    @NotNull
    private String trxTm;
    @NotNull
    private String cardHolder;
    @NotNull
    private String cardNo;
    private String buyerEmail;
    private String buyerPhone;
    private String citizenIdentity;
    @NotNull
    private String amount;
    @NotNull
    private String authCode;
    @NotNull
    private String bankId;
    @NotNull
    private String termIs;
    @NotNull
    private String notiUrl;
    private String mid;
    private String tid;

    public String merchantTokenEncoding(String encodeKey){
        return Encode.enscriptSHA256(timeStamp + merTrxId + merId +
                cardHolder + cardNo + amount + authCode +
                bankId + termIs + notiUrl + encodeKey);
    }
}
