package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.PreferDao;
import com.actstrady.wmall.po.PreferPO;
import com.actstrady.wmall.service.PreferService;
import com.actstrady.wmall.vo.PreferVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PreferServiceImpl implements PreferService {
    private final PreferDao preferDao;

    public PreferServiceImpl(PreferDao preferDao) {
        this.preferDao = preferDao;
    }

    private List<PreferVO> buildPreferList(List<PreferPO> preferPOS){
        if(preferPOS ==null || preferPOS.size()==0){
            return new ArrayList<PreferVO>(0);
        }

        List<PreferVO> result = new ArrayList<>();
        for(PreferPO item: preferPOS){
            PreferVO glist = buildPrefer(item);
            result.add(glist);
        }
        return result;
    }

    private PreferVO buildPrefer(PreferPO preferPO){
        PreferVO item = new PreferVO();
        item.setUserId(preferPO.getUserId());
        item.setCategoryId(preferPO.getCategoryId());
        item.setId(preferPO.getId());
        item.setCreateTime(preferPO.getCreateTime());
      return item;
    }

    @Override
    public List<PreferVO> getByUserId(int userId){
        return buildPreferList(preferDao.getByUserId(userId));
    }

    @Override
    public void insertInfo(int userId,int categoryId){
        PreferPO preferPO = new PreferPO();
        preferPO.setUserId(userId);
        preferPO.setCategoryId(categoryId);
        preferPO.setCreateTime(new Date());
        preferDao.save(preferPO);
    }
}
