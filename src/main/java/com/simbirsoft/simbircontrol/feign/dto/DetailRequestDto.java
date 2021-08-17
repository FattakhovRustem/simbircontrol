package com.simbirsoft.simbircontrol.feign.dto;

public class DetailRequestDto {

    private Long transaction;

    public DetailRequestDto() {
    }

    public DetailRequestDto(Long transaction) {
        this.transaction = transaction;
    }

    public Long getTransaction() {
        return transaction;
    }

    public void setTransaction(Long transaction) {
        this.transaction = transaction;
    }
}
