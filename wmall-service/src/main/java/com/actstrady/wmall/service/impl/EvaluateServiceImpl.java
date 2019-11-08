package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.EvaluateDao;
import com.actstrady.wmall.po.EvaluatePO;
import com.actstrady.wmall.service.EvaluateService;
import com.actstrady.wmall.utils.ListCopy;
import com.actstrady.wmall.vo.EvaluateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EvaluateServiceImpl implements EvaluateService {
    private final EvaluateDao evaluateDao;
    private final ListCopy<EvaluatePO, EvaluateVO> listCopy;

    public EvaluateServiceImpl(EvaluateDao evaluateDao, ListCopy listCopy) {
        this.evaluateDao = evaluateDao;
        this.listCopy = listCopy;
    }

    // private List<EvaluateVO> buildEvaluateList(List<Evaluate> evaluates) {
    //     if (evaluates == null || evaluates.size() == 0) {
    //         return new ArrayList<>(0);
    //     }
    //     List<EvaluateVO> result = new ArrayList<>();
    //     for (Evaluate item : evaluates) {
    //         EvaluateVO elst = buildEvaluate(item);
    //         result.add(elst);
    //     }
    //     return result;
    // }

    private EvaluateVO buildEvaluate(EvaluatePO item) {
        EvaluateVO elst = new EvaluateVO();
        elst.setId(item.getId());
        elst.setUserId(item.getUserId());
        elst.setGoodsId(item.getGoodsId());
        elst.setComment(item.getComment());
        elst.setGrade(item.getGrade());
        elst.setUserName(item.getUserName());
        elst.setCreateTime(item.getCreateTime());
        elst.setCartId(item.getCartId());
        return elst;
    }

    @Override
    public List<EvaluateVO> getByGood(int goodsId, int pageSize, int pageIndex) {
        return listCopy.listBuild(evaluateDao.getByGoodsId(goodsId, pageIndex * pageSize, pageSize), EvaluateVO.class);
    }

    @Override
    public EvaluateVO getByCartId(int cartId) {
        return buildEvaluate(evaluateDao.getByCartId(cartId));
    }

    @Override
    public EvaluatePO insertEvaluateInfo(EvaluatePO data) {
        return evaluateDao.save(data);
    }

    ;
}
