package com.gurpreet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Entity
@Table(name = "bond_details")
@AllArgsConstructor
@NoArgsConstructor
public class BondDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "bond_name")
    private String bondName;

    @NotNull
    @Column(name = "bond_description")
    private String bondDescription;

    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "return_percentage")
    private Double returnPercentage;

    @NotNull
    @Column(name = "profit_prediction")
    private Double profitPrediction;

}
