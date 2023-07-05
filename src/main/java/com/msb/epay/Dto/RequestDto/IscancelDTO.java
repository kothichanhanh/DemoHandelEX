package com.msb.epay.Dto.RequestDto;

import javax.validation.constraints.*;

public class IscancelDTO {
    @NotNull
    private String trxId;
    @NotNull
    private String merId;
    @NotNull
    private String merTrxId;
    @NotNull
    private String type;
    private String cancelMsg;
    @NotNull
    private String timeStamp;
    private String merchantToken;
}
