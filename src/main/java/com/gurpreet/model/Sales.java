package com.gurpreet.model;
import com.gurpreet.enums.SaleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Data
@Entity
@Table(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "bond_id")
    private int bondId;

    @NotNull
    @Column(name = "buyer_id")
    private int buyerId;

    @NotNull
    @Column(name = "seller_id")
    private int sellerId;

    @NotNull
    @Column(name = "sale_type")
    @Enumerated(EnumType.STRING)
    private SaleType saleType;

    @NotNull
    @Column(name = "created_date")
    private Date createdDate;

    @NotNull
    @Column(name = "updated_date")
    private Date updatedDate;

}