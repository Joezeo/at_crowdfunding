package com.joezeo.atcrowdfunding.common.vo;

import com.joezeo.atcrowdfunding.bean.MemberCert;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于接收前端传来的多参数集合\数组
 */
public class ParamModel {
    List<MemberCert> certimgs = new ArrayList<>();

    public List<MemberCert> getCertimgs() {
        return certimgs;
    }

    public void setCertimgs(List<MemberCert> certimgs) {
        this.certimgs = certimgs;
    }
}
