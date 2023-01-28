package com.company.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Book {
    private Long id;

    private String name;

    private BigDecimal price;

    private String desc;
}
