package com.example.pincodesurveyor.controllers;

import com.example.pincodesurveyor.dto.ApiResponse;
import com.example.pincodesurveyor.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @GetMapping("/{pincode}")
    public ResponseEntity<List<ApiResponse>> getPostOfficeByPinCode(@PathVariable String pincode) {
        return ResponseEntity.status(HttpStatus.OK).body(appService.getPostOfficeByPinCode(pincode));
    }

}
