package com.example.pincodesurveyor.services;

import com.example.pincodesurveyor.dto.ApiResponse;
import com.example.pincodesurveyor.dto.ApiResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppService {
    private final RestTemplate restTemplate;

    public List<ApiResponse> getPostOfficeByPinCode(String pincode) {
        String url = "https://api.postalpincode.in/pincode/" + pincode;
        List<Map<String, Object>> mapList = null;

        try {
            log.info("Connecting to the remote api !!!");
            mapList = restTemplate.getForObject(url, ArrayList.class);
        } catch (RuntimeException e) {
            log.info("Unable to connect to remote api : {}", e.getMessage());
        }

        ApiResponseWrapper wrapper = new ApiResponseWrapper();
        wrapper.setMap((List<Map<String, String>>) mapList.get(0).get("PostOffice"));

        List<ApiResponse> apiResponseList = new ArrayList<>();

        for(int i=0; i<wrapper.getMap().size(); i++) {
            Map<String, String> postOfficeDetailsObject = wrapper.getMap().get(i);
            ApiResponse apiResponse = new ApiResponse();

            apiResponse.setName(postOfficeDetailsObject.get("Name"));
            apiResponse.setState(postOfficeDetailsObject.get("State"));
            apiResponse.setCountry(postOfficeDetailsObject.get("Country"));
            apiResponse.setPincode(postOfficeDetailsObject.get("Pincode"));

            apiResponseList.add(apiResponse);
        }

        return apiResponseList;
    }
}
