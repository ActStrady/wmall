package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.EvaluateDao;
import com.actstrady.wmall.dao.GoodsCartDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.po.EvaluatePO;
import com.actstrady.wmall.po.GoodsPO;
import com.actstrady.wmall.po.GoodsCartPO;
import com.actstrady.wmall.service.GoodsCartService;
import com.actstrady.wmall.vo.GoodsCartVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoodsCartServiceImpl implements GoodsCartService {
    private final GoodsCartDao goodsCartDao;
    private final GoodsDao goodsDao;
    private final CategoryDao categoryDao;
    private final EvaluateDao evaluateDao;

    public GoodsCartServiceImpl(GoodsCartDao goodsCartDao, GoodsDao goodsDao, CategoryDao categoryDao, EvaluateDao evaluateDao) {
        this.goodsCartDao = goodsCartDao;
        this.goodsDao = goodsDao;
        this.categoryDao = categoryDao;
        this.evaluateDao = evaluateDao;
    }

    private List<GoodsCartVO> buildGoodsCartList(List<GoodsCartPO> goodsCartPOS) {
        if (goodsCartPOS == null || goodsCartPOS.size() == 0) {
            return new ArrayList<>(0);
        }

        List<GoodsCartVO> result = new ArrayList<>();
        for (GoodsCartPO item : goodsCartPOS) {
            GoodsCartVO gList = buildGoodsCart(item);
            result.add(gList);
        }
        return result;
    }

    private GoodsCartVO buildGoodsCart(GoodsCartPO goodsCartPO) {
        GoodsCartVO item = new GoodsCartVO();
        item.setId(goodsCartPO.getId());
        item.setUserId(goodsCartPO.getUserId());
        item.setGoodsId(goodsCartPO.getGoodsId());
        item.setNumber(goodsCartPO.getNumber());
        item.setStatus(goodsCartPO.getStatus());
        item.setCreateTime(goodsCartPO.getCreateTime());
        GoodsPO goodInfo = goodsDao.getOne(goodsCartPO.getGoodsId());
        item.setGoodsPO(goodInfo);
        item.setParentCategoryId(categoryDao.getOne(goodInfo.getCategoryId()).getParentId());
        EvaluatePO eList = evaluateDao.getByCartId(goodsCartPO.getId());
        if (eList != null) {
            item.setEvaluateId(eList.getId());
        }
        return item;
    }

    @Override
    public List<GoodsCartVO> getByUser(int userId, int status, int pageSize, int pageIndex) {
        Page<GoodsCartPO> goodsPage = goodsCartDao.getByUserIdAndStatus(userId, status, PageRequest.of(pageIndex * pageSize, pageSize));
        return buildGoodsCartList(goodsPage.getContent());
    }

    @Override
    public void deleteById(int cartId) {
        goodsCartDao.deleteById(cartId);
    }

    @Override
    public void updateById(int cartId, int number) {
        GoodsCartPO goodsCartPO = goodsCartDao.getOne(cartId);
        goodsCartPO.setNumber(number);
        goodsCartPO.setStatus(1);
        goodsCartDao.save(goodsCartPO);
    }

    @Override
    public List<GoodsCartVO> getByInfo(int userId, int goodsId, int status) {
        return buildGoodsCartList(goodsCartDao.getByUserIdAndStatusAndGoodsId(userId, goodsId, status));
    }

    @Override
    public void insertCartInfo(int userId, int goodsId, int number, int status) {
        GoodsCartPO goodsCartPO = new GoodsCartPO();
        goodsCartPO.setUserId(userId);
        goodsCartPO.setGoodsId(goodsId);
        goodsCartPO.setNumber(number);
        goodsCartPO.setStatus(status);
        goodsCartPO.setCreateTime(new Date());
        goodsCartDao.save(goodsCartPO);
    }

    @Override
    public void addCartCountById(int cartId) {
        GoodsCartPO goodsCartPO = goodsCartDao.getOne(cartId);
        goodsCartPO.setNumber(goodsCartPO.getNumber() + 1);
        goodsCartPO.setCreateTime(new Date());
        goodsCartDao.save(goodsCartPO);
    }

    /**
     * 根据用户编号获取已购买的商品信息
     *
     * @param userId    用户编号
     * @param pageSize  每页显示的条数
     * @param pageIndex 当前页码
     * @return 用于展示的商品列表
     */
    @Override
    public List<GoodsCartVO> getPurchasedGoodByUserId(int userId, int pageSize, int pageIndex) {
        Page<GoodsCartPO> goodsCartsPage = goodsCartDao.getByUserIdAndStatus(userId, 1, PageRequest.of(pageIndex * pageSize, pageSize));
        return buildGoodsCartList(goodsCartsPage.getContent());
    }
}
