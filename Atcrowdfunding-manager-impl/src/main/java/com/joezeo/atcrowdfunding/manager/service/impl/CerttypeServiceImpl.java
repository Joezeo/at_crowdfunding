package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.bean.AccountTypeCert;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.manager.mapper.AccountTypeCertMapper;
import com.joezeo.atcrowdfunding.manager.service.CerttypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CerttypeServiceImpl implements CerttypeService {

    @Resource
    private AccountTypeCertMapper accountTypeCertMapper;

    public List<AccountTypeCert> queryAll() {
        List<AccountTypeCert> list = accountTypeCertMapper.selectAll();
        if(list == null){
            throw new ServiceException("查询账户类型资质分配信息失败");
        }
        return list;
    }

    public void addCerttype(AccountTypeCert certtype) {
        if(certtype == null){
            throw new ServiceException("传入参数certtype不可为null");
        }

        int count = accountTypeCertMapper.insertSelective(certtype);
        if(count != 1){
            throw new ServiceException("新增会员账户类型失败");
        }
    }

    public void removeCerttype(AccountTypeCert certtype) {
        if(certtype == null){
            throw new ServiceException("传入参数certtype不可为null");
        }

        int count = accountTypeCertMapper.deleteByAccttypeCertid(certtype);
        if(count != 1){
            throw new ServiceException("删除会员账户类型失败");
        }
    }

}
