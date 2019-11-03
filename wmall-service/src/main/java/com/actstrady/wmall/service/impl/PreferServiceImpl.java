package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.PreferDao;
import com.actstrady.wmall.po.Prefer;
import com.actstrady.wmall.service.PreferService;
import com.actstrady.wmall.vo.PreferList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PreferServiceImpl implements PreferService {
    private final PreferDao preferDao;

    @Autowired
    public PreferServiceImpl(PreferDao preferDao) {
        this.preferDao = preferDao;
    }

    private List<PreferList> buildPreferList(List<Prefer> prefers){
        if(prefers==null || prefers.size()==0){
            return new ArrayList<PreferList>(0);
        }

        List<PreferList> result = new ArrayList<>();
        for(Prefer item:prefers){
            PreferList glist = buildPrefer(item);
            result.add(glist);
        }
        return result;
    }

    private PreferList buildPrefer(Prefer prefer){
        PreferList item = new PreferList();
        item.setUserId(prefer.getUserId());
        item.setCategoryId(prefer.getCategoryId());
        item.setId(prefer.getId());
        item.setCreateTime(prefer.getCreateTime());
      return item;
    }

    @Override
    public List<PreferList> getByUserId(int userId){
        return buildPreferList(preferDao.getByUserId(userId));
    }

    @Override
    public void insertInfo(int userId,int categoryId){
        Prefer prefer = new Prefer();
        prefer.setUserId(userId);
        prefer.setCategoryId(categoryId);
        prefer.setCreateTime(new Date());
        preferDao.save(prefer);
    }
}
