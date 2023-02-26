package com.noritakakagei.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
    private Integer id;
    private String name;
}