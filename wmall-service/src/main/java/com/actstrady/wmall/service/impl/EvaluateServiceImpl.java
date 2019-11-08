package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.EvaluateDao;
import com.actstrady.wmall.dao.UserDao;
import com.actstrady.wmall.po.EvaluatePO;
import com.actstrady.wmall.po.UserPO;
import com.actstrady.wmall.service.EvaluateService;
import com.actstrady.wmall.utils.ListCopy;
import com.actstrady.wmall.vo.EvaluateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EvaluateServiceImpl implements EvaluateService {
    private final EvaluateDao evaluateDao;
    private final UserDao userDao;
    private final ListCopy<EvaluatePO, EvaluateVO> listCopy;

    public EvaluateServiceImpl(EvaluateDao evaluateDao, UserDao userDao, ListCopy<EvaluatePO, EvaluateVO> listCopy) {
        this.evaluateDao = evaluateDao;
        this.userDao = userDao;
        this.listCopy = listCopy;
    }

    @Override
    public List<EvaluateVO> getByGood(int goodsId, int pageSize, int pageIndex) {
        Page<EvaluatePO> pageEvaluate = evaluateDao.getByGoodsId(goodsId, PageRequest.of(pageIndex * pageSize, pageSize));
        // 获取评价人姓名
        List<EvaluateVO> evaluates = listCopy.listBuild(pageEvaluate.getContent(), EvaluateVO.class);
        for (EvaluateVO evaluateVO : evaluates) {
            UserPO user = userDao.getOne(evaluateVO.getUserId());
            evaluateVO.setUserName(user.getUsername());
        }
        return evaluates;
    }

    @Override
    public EvaluateVO getByCartId(int cartId) {
        return listCopy.beanBuild(evaluateDao.getByCartId(cartId), EvaluateVO.class);
    }

    @Override
    public EvaluatePO insertEvaluateInfo(EvaluatePO data) {
        return evaluateDao.save(data);
    }
}
