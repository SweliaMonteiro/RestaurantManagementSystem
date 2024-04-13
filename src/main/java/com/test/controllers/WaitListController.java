package com.test.controllers;

import com.test.dtos.*;
import com.test.enums.ResponseStatus;
import com.test.services.WaitListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class WaitListController {

    @Autowired
    public WaitListService waitListService;


    public AddUserToWaitListResponseDto addUserToWaitList(AddUserToWaitListRequestDto requestDto) {
        AddUserToWaitListResponseDto responseDto = new AddUserToWaitListResponseDto();
        try {
            int position = waitListService.addUserToWaitList(requestDto.getUserId());
            responseDto.setPosition(position);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }


    public RemoveUserFromWaitListResponseDto removeUserFromWaitList(RemoveUserFromWaitListRequestDto requestDto) {
        RemoveUserFromWaitListResponseDto responseDto = new RemoveUserFromWaitListResponseDto();
        try {
            waitListService.removeUserFromWaitList(requestDto.getUserId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }


    public GetUserWaitListResponseDto getWaitListStatus(GetUserWaitListRequestDto requestDto) {
        GetUserWaitListResponseDto responseDto = new GetUserWaitListResponseDto();
        try {
            int position = waitListService.getWaitListPosition(requestDto.getUserId());
            responseDto.setPosition(position);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }


    public UpdateWaitListResponseDto updateWaitList(UpdateWaitListRequestDto requestDto) {
        UpdateWaitListResponseDto responseDto = new UpdateWaitListResponseDto();
        try {
            waitListService.updateWaitList(requestDto.getUserId(), requestDto.getNumberOfSeats());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
