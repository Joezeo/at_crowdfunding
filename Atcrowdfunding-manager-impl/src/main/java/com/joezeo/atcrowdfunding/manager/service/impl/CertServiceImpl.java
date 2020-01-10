package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.bean.Cert;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.manager.mapper.CertMapper;
import com.joezeo.atcrowdfunding.manager.service.CertService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CertServiceImpl implements CertService {

    @Resource
    private CertMapper certMapper;

    public PageInfo queryCertByPage(Integer pageSize, Integer pageNum, String name) {
        if(pageSize == null || pageSize<=0 || pageNum==null || pageNum<=0){
            throw new ServiceException("传入分页相关参数错误，查询失败");
        }

        PageInfo pageInfo = new PageInfo(pageSize, pageNum);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageInfo", pageInfo);
        map.put("name", name);

        List<Cert> list = certMapper.selectByPage(map);
        if (list == null) {
            throw new ServiceException("分页查询角色信息失败");
        }
        pageInfo.setDatas(list);

        int count = certMapper.selectCount(name);
        if (count < 0) {
            throw new ServiceException("分页查询角色信息失败，count<0");
        }
        pageInfo.setPageTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

        return pageInfo;
    }

    public void addCert(Cert cert) {
        if(cert == null){
            throw new ServiceException("传入参数不可为null");
        }

        int count = certMapper.insert(cert);
        if(count != 1){
            throw new ServiceException("添加资质失败，请稍后重试");
        }
    }

    public void deleteCertBatch(Integer[] ids) {
        if(ids == null){
            throw new ServiceException("传入的参数不可为null");
        }

        for(Integer id : ids){
            int count = certMapper.deleteByPrimaryKey(id);
            if(count != 1){
                throw new ServiceException("删除资质失败，请稍后重试");
            }
        }
    }

    public void deleteCert(Integer id) {
        if(id == null || id<=0){
            throw new ServiceException("传入资质id值异常");
        }

        int count = certMapper.deleteByPrimaryKey(id);
        if(count != 1){
            throw new ServiceException("删除资质失败，请稍后重试");
        }
    }

    public Cert queryCert(Integer id) {
        if(id == null || id<=0){
            throw new ServiceException("传入id值异常");
        }
        Cert cert = certMapper.selectByPrimaryKey(id);
        if(cert == null){
            throw new ServiceException("查询资质信息失败");
        }
        return cert;
    }
}
