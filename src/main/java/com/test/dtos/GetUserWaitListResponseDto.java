package com.test.dtos;

import com.test.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserWaitListResponseDto {

    private int position;

    private ResponseStatus responseStatus;

}