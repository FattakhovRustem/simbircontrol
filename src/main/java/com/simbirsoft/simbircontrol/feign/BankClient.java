package com.simbirsoft.simbircontrol.feign;

import com.simbirsoft.simbircontrol.feign.dto.AccountResponseDto;
import com.simbirsoft.simbircontrol.feign.dto.DetailRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "bank", url = "${bank.api-base-url}")
public interface BankClient {

    @GetMapping(value = "/account/{number}/balance")
    AccountResponseDto getBalanceAccount(@PathVariable Integer number);

    @PutMapping(value = "/account/{number}/operation")
    AccountResponseDto makeOperation(@PathVariable Integer number, @RequestBody DetailRequestDto requestDto);
}
