package com.joezeo.atcrowdfunding.manager.mapper;

import com.joezeo.atcrowdfunding.bean.AccountTypeCert;

import java.util.List;

public interface AccountTypeCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountTypeCert record);

    int insertSelective(AccountTypeCert record);

    AccountTypeCert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountTypeCert record);

    int updateByPrimaryKey(AccountTypeCert record);

    List<AccountTypeCert> selectAll();

    int deleteByAccttypeCertid(AccountTypeCert certtype);
}