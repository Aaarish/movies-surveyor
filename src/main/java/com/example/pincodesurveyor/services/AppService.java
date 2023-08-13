package com.example.pincodesurveyor.services;

import com.example.pincodesurveyor.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppService {
    private final RestTemplate restTemplate;

    public ApiResponse getPostOfficeByPinCode(String pincode) {
        String url = "https://api.postalpincode.in/pincode/" + pincode;
        List<Map<String, Object>> mapList = null;

        try {
            System.out.println("Connecting to the remote api !!!");
            mapList = restTemplate.getForObject(url, ArrayList.class);

        } catch (RuntimeException e) {
            throw new RuntimeException("Unable to connect to remote api !");
        }

        Map<String, Object> map = mapList.get(0);
        List<Map<String, String>> postOfficeDetails = (List<Map<String, String>>) map.get("PostOffice");
        Map<String, String> postOfficeDetailsDefault = postOfficeDetails.get(2);
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setName(postOfficeDetailsDefault.get("Name"));
        apiResponse.setState(postOfficeDetailsDefault.get("State"));
        apiResponse.setCountry(postOfficeDetailsDefault.get("Country"));
        apiResponse.setPincode(postOfficeDetailsDefault.get("Pincode"));

        return apiResponse;
    }
}
