package com.example.dtos;

import com.example.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserToWaitListResponseDto {

    private int position;

    private ResponseStatus responseStatus;

}
