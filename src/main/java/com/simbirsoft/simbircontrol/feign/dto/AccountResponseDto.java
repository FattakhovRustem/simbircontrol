package com.simbirsoft.simbircontrol.feign.dto;

public class AccountResponseDto {

    private Integer id;
    private Long balance;

    public AccountResponseDto() {
    }

    public AccountResponseDto(Integer id, Long balance) {
        this.id = id;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
