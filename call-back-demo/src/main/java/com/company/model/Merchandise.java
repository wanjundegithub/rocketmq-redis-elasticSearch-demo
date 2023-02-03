package com.company.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
public class Merchandise {
    private String name;
    private BigDecimal price;
}
