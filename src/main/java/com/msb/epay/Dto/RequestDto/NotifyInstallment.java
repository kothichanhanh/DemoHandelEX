package com.msb.epay.Dto.RequestDto;

import lombok.*;

import javax.validation.constraints.*;
import java.security.*;

@Data
@AllArgsConstructor
public class NotifyInstallment {
    @NotNull
    @Size(max = 6)
    private String resultCd;
    @NotNull
    @Size(max = 512)
    private String resultMsg;
    @NotNull
    @Size(max = 10)
    private String merId;
    @NotNull
    @Size(max = 30)
    private String trxId;
    @NotNull
    @Size(max = 50)
    private String merTrxId;
    @NotNull
    @Size(max = 1)
    private String type;

    private Timestamp timeStamp;

    @NotNull
    @Size(max = 255)
    private Timestamp merchantToken;

}
