package com.joezeo.atcrowdfunding.potal.mapper;

import com.joezeo.atcrowdfunding.bean.MemberCert;

public interface MemberCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberCert record);

    int insertSelective(MemberCert record);

    MemberCert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberCert record);

    int updateByPrimaryKey(MemberCert record);
}