package com.joezeo.atcrowdfunding.potal.service.impl;

import com.joezeo.atcrowdfunding.bean.MemberCert;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.potal.mapper.MemberCertMapper;
import com.joezeo.atcrowdfunding.potal.service.MemberCertService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberCertServiceImpl implements MemberCertService {

    @Resource
    private MemberCertMapper memberCertMapper;

    public void addMemberCertList(List<MemberCert> certimgs) {
        if(certimgs == null){
            throw new ServiceException("传入参数certimgs不可为空");
        }

        for(MemberCert memberCert : certimgs){
            int count = memberCertMapper.insertSelective(memberCert);
            if(count != 1){
                throw new ServiceException("保存会员资质信息失败，请稍后重试");
            }
        }
    }
}
