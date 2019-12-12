package com.joezeo.atcrowdfunding.common.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于接收前端传来的多参数集合\数组
 */
public class ParamModel {
    private List<Integer> integerList = new ArrayList<>();

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }
}
