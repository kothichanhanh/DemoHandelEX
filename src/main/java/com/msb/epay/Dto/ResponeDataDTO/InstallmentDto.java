package com.msb.epay.Dto.ResponeDataDTO;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstallmentDto {
    private String amount;
    private String resultCd;
    private String resultMsg;
    private String userFee;
    private String merFee;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String data;
    private Object installmentInfo;
}
