package com.example.pincodesurveyor.controllers;

import com.example.pincodesurveyor.dto.ApiResponse;
import com.example.pincodesurveyor.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @GetMapping("/{pincode}")
    public ApiResponse getPostOfficeByPinCode(@PathVariable String pincode) {
        return appService.getPostOfficeByPinCode(pincode);
    }

}
