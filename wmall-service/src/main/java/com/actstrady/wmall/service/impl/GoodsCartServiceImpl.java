package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.EvaluateDao;
import com.actstrady.wmall.dao.GoodsCartDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.po.Evaluate;
import com.actstrady.wmall.po.Goods;
import com.actstrady.wmall.po.GoodsCart;
import com.actstrady.wmall.service.GoodsCartService;
import com.actstrady.wmall.vo.GoodsCartList;
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

    private List<GoodsCartList> buildGoodsCartList(List<GoodsCart> goodsCarts) {
        if (goodsCarts == null || goodsCarts.size() == 0) {
            return new ArrayList<>(0);
        }

        List<GoodsCartList> result = new ArrayList<>();
        for (GoodsCart item : goodsCarts) {
            GoodsCartList gList = buildGoodsCart(item);
            result.add(gList);
        }
        return result;
    }

    private GoodsCartList buildGoodsCart(GoodsCart goodsCart) {
        GoodsCartList item = new GoodsCartList();
        item.setId(goodsCart.getId());
        item.setUserId(goodsCart.getUserId());
        item.setGoodsId(goodsCart.getGoodsId());
        item.setNumber(goodsCart.getNumber());
        item.setStatus(goodsCart.getStatus());
        item.setCreateTime(goodsCart.getCreateTime());
        Goods goodInfo = goodsDao.getOne(goodsCart.getGoodsId());
        item.setGoods(goodInfo);
        item.setParentCategoryId(categoryDao.getOne(goodInfo.getCategoryId()).getParentId());
        Evaluate eList = evaluateDao.getByCartId(goodsCart.getId());
        if (eList != null) {
            item.setEvaluateId(eList.getId());
        }
        return item;
    }

    @Override
    public List<GoodsCartList> getByUser(int userId, int status, int pageSize, int pageIndex) {
        Page<GoodsCart> goodsPage = goodsCartDao.getByUserIdAndStatus(userId, status, PageRequest.of(pageIndex * pageSize, pageSize));
        return buildGoodsCartList(goodsPage.getContent());
    }

    @Override
    public void deleteById(int cartId) {
        goodsCartDao.deleteById(cartId);
    }

    @Override
    public void updateById(int cartId, int number) {
        GoodsCart goodsCart = goodsCartDao.getOne(cartId);
        goodsCart.setNumber(number);
        goodsCart.setStatus(1);
        goodsCartDao.save(goodsCart);
    }

    @Override
    public List<GoodsCartList> getByInfo(int userId, int goodsId, int status) {
        return buildGoodsCartList(goodsCartDao.getByUserIdAndStatusAndGoodsId(userId, goodsId, status));
    }

    @Override
    public void insertCartInfo(int userId, int goodsId, int number, int status) {
        GoodsCart goodsCart = new GoodsCart();
        goodsCart.setUserId(userId);
        goodsCart.setGoodsId(goodsId);
        goodsCart.setNumber(number);
        goodsCart.setStatus(status);
        goodsCart.setCreateTime(new Date());
        goodsCartDao.save(goodsCart);
    }

    @Override
    public void addCartCountById(int cartId) {
        GoodsCart goodsCart = goodsCartDao.getOne(cartId);
        goodsCart.setNumber(goodsCart.getNumber() + 1);
        goodsCart.setCreateTime(new Date());
        goodsCartDao.save(goodsCart);
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
    public List<GoodsCartList> getPurchasedGoodByUserId(int userId, int pageSize, int pageIndex) {
        Page<GoodsCart> goodsCartsPage = goodsCartDao.getByUserIdAndStatus(userId, 1, PageRequest.of(pageIndex * pageSize, pageSize));
        return buildGoodsCartList(goodsCartsPage.getContent());
    }
}
