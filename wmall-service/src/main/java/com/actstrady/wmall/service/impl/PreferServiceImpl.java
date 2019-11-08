package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.PreferDao;
import com.actstrady.wmall.po.PreferPO;
import com.actstrady.wmall.service.PreferService;
import com.actstrady.wmall.utils.ListCopy;
import com.actstrady.wmall.vo.PreferVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PreferServiceImpl implements PreferService {
    private final PreferDao preferDao;
    private final ListCopy<PreferPO, PreferVO> listCopy;

    public PreferServiceImpl(PreferDao preferDao, ListCopy<PreferPO, PreferVO> listCopy) {
        this.preferDao = preferDao;
        this.listCopy = listCopy;
    }

    private List<PreferVO> buildPreferList(List<PreferPO> preferPos) {
        if (preferPos == null || preferPos.size() == 0) {
            return new ArrayList<PreferVO>(0);
        }
        List<PreferVO> result = new ArrayList<>();
        for (PreferPO preferPo : preferPos) {
            PreferVO preferVo = listCopy.beanBuild(preferPo, PreferVO.class);
            result.add(preferVo);
        }
        return result;
    }

    @Override
    public List<PreferVO> getByUserId(int userId) {
        return buildPreferList(preferDao.getByUserId(userId));
    }

    @Override
    public void insertInfo(int userId, int categoryId) {
        PreferPO preferPo = new PreferPO();
        preferPo.setUserId(userId);
        preferPo.setCategoryId(categoryId);
        preferPo.setCreateTime(new Date());
        preferDao.save(preferPo);
    }
}
