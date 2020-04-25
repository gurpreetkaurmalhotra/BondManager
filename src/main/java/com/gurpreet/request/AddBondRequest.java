package com.gurpreet.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddBondRequest {

    Integer userId;
    String bondName;
    String bondDescription;
    Double price;
    Double returnPercentage;
    Double profitPredictionPercentage;

}
