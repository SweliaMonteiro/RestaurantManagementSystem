package com.test.dtos;

import com.test.enums.ResponseStatus;
import com.test.models.Bill;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateBillResponseDto {

    private ResponseStatus responseStatus;

    private Bill bill;

}

