package com.example.dtos;

import com.example.enums.ResponseStatus;
import com.example.models.Bill;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateBillResponseDto {

    private ResponseStatus responseStatus;

    private Bill bill;

}

