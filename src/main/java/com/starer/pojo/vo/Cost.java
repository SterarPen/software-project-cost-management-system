package com.starer.pojo.vo;

import java.math.BigDecimal;

/**
 * @Author: pengxiong
 * @Date: 2023/12/28 16:26:54
 * @Version: V1.0
 * @Description:
 **/
public class Cost {
    private String name;
    private String value;

    public Cost() {
    }



    public Cost(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
