package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.EvaluateDao;
import com.actstrady.wmall.po.Evaluate;
import com.actstrady.wmall.service.EvaluateService;
import com.actstrady.wmall.vo.EvaluateList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {
    private final EvaluateDao evaluateDao;

    @Autowired
    public EvaluateServiceImpl(EvaluateDao evaluateDao) {
        this.evaluateDao = evaluateDao;
    }

    private List<EvaluateList> buildEvaluateList(List<Evaluate> evaluates) {
        if (evaluates == null || evaluates.size() == 0) {
            return new ArrayList<>(0);
        }
        List<EvaluateList> result = new ArrayList<>();
        for (Evaluate item : evaluates) {
            EvaluateList elst = buildEvaluate(item);
            result.add(elst);
        }
        return result;
    }

    private EvaluateList buildEvaluate(Evaluate item) {
        EvaluateList elst = new EvaluateList();
        elst.setId(item.getId());
        elst.setComment(item.getComment());
        elst.setGrade(item.getGrade());
        elst.setUserName(item.getUserName());
        elst.setCreateTime(item.getCreateTime());
        elst.setCartId(item.getCartId());
        return elst;
    }

    @Override
    public List<EvaluateList> getByGood(int goodsId, int pageSize, int pageIndex) {
        Page<Evaluate> goodsPage = evaluateDao.getByGoodsId(goodsId, PageRequest.of(pageIndex * pageSize, pageSize));
        return buildEvaluateList(goodsPage.getContent());
    }

    @Override
    public EvaluateList getByCartId(int cartId) {
        return buildEvaluate(evaluateDao.getByCartId(cartId));
    }

    @Override
    public Evaluate insertEvaluateInfo(Evaluate data) {
        return evaluateDao.save(data);
    }

    ;
}
