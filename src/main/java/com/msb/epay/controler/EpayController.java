package com.msb.epay.controler;

import com.msb.epay.Dto.RequestDto.*;
import com.msb.epay.Dto.*;
import com.msb.epay.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@Slf4j
@RestController
@RequestMapping("/epay")
public class EpayController {

    @Autowired
    EpayService epayService;

    @GetMapping("/ping")
    public String ping(){
        return "pong!";
    }

    @GetMapping("/get-installmeant-info")
    public ResponseEntity<ResponeDto> getInstallmentInfo(@RequestParam(required = true)
                                                         String amount){
        ResponeDto<String> responeDto = epayService.getInfoInstallmant(amount);
        return ResponseEntity.ok().body(responeDto);
    }

    @PostMapping("/change-to-installment")
    public ResponseEntity<ResponeDto> changeToInstallmen(@RequestBody @Valid ChageToIstallmentDto chageToIstallmentDto){
        ResponeDto<String> responeDto = epayService.changeToInstallment(chageToIstallmentDto);
        return ResponseEntity.ok().body(responeDto);
    }

    @PostMapping("/is-cancel")
    public ResponseEntity<ResponeDto> Iscancel(@RequestBody @Valid NotifyInstallment notifyInstallment){
        ResponeDto<String> responeDto = new ResponeDto<>();
        return ResponseEntity.ok().body(responeDto);
    }

    @PostMapping("/notify-installment")
    public ResponseEntity<ResponeDto> notifyInstallmen(@RequestBody @Valid NotifyInstallment notifyInstallment){
        ResponeDto<String> responeDto = new ResponeDto<>();
        return ResponseEntity.ok().body(responeDto);
    }
}
