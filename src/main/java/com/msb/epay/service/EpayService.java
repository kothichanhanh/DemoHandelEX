package com.msb.epay.service;

import com.msb.epay.Dto.*;
import com.msb.epay.Dto.RequestDto.*;

public interface EpayService {
    ResponeDto getInfoInstallmant(String amount);

    ResponeDto changeToInstallment(ChageToIstallmentDto chageToIstallmentDto);
}
