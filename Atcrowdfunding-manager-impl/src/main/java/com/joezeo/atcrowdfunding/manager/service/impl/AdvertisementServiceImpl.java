package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.bean.Advertisement;
import com.joezeo.atcrowdfunding.bean.Role;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.manager.mapper.AdvertisementMapper;
import com.joezeo.atcrowdfunding.manager.service.AdvertisementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Resource
    private AdvertisementMapper advertisementMapper;

    @SuppressWarnings("Duplicates")
    public PageInfo queryAdvertByPage(String name, Integer pageSize, Integer pageNum) {
        if(pageSize == null || pageSize<=0 || pageNum==null || pageNum<=0){
            throw new ServiceException("传入分页相关参数错误，查询失败");
        }

        PageInfo pageInfo = new PageInfo(pageSize, pageNum);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageInfo", pageInfo);
        map.put("name", name);

        List<Advertisement> list = advertisementMapper.selectByPage(map);
        if (list == null) {
            throw new ServiceException("分页查询角色信息失败");
        }
        pageInfo.setDatas(list);

        int count = advertisementMapper.selectCount(name);
        if (count < 0) {
            throw new ServiceException("分页查询角色信息失败，count<0");
        }
        pageInfo.setPageTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

        return pageInfo;
    }

    public void addAdvert(Advertisement advert) {
        if(advert == null){
            throw new ServiceException("传入的广告参数异常，=null");
        }

        int count = advertisementMapper.insertSelective(advert);
        if(count != 1){
            throw new ServiceException("新增广告失败！");
        }
    }
}
