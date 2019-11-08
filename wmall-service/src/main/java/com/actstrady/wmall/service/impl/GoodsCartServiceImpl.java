package com.actstrady.wmall.service.impl;

import com.actstrady.wmall.dao.CategoryDao;
import com.actstrady.wmall.dao.EvaluateDao;
import com.actstrady.wmall.dao.GoodsCartDao;
import com.actstrady.wmall.dao.GoodsDao;
import com.actstrady.wmall.po.EvaluatePO;
import com.actstrady.wmall.po.GoodsPO;
import com.actstrady.wmall.po.GoodsCartPO;
import com.actstrady.wmall.service.GoodsCartService;
import com.actstrady.wmall.utils.ListCopy;
import com.actstrady.wmall.vo.GoodsCartVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsCartServiceImpl implements GoodsCartService {
    private final GoodsCartDao goodsCartDao;
    private final GoodsDao goodsDao;
    private final CategoryDao categoryDao;
    private final EvaluateDao evaluateDao;
    private final ListCopy<GoodsCartPO, GoodsCartVO> listCopy;

    public GoodsCartServiceImpl(GoodsCartDao goodsCartDao, GoodsDao goodsDao, CategoryDao categoryDao, EvaluateDao evaluateDao, ListCopy<GoodsCartPO, GoodsCartVO> listCopy) {
        this.goodsCartDao = goodsCartDao;
        this.goodsDao = goodsDao;
        this.categoryDao = categoryDao;
        this.evaluateDao = evaluateDao;
        this.listCopy = listCopy;
    }

    /**
     * 添加额外的属性
     * @param goodsCarts 原list
     */
    private void addAttribute(List<GoodsCartVO> goodsCarts) {
        for (GoodsCartVO goodsCart : goodsCarts) {
            GoodsPO goods = goodsDao.getOne(goodsCart.getGoodsId());
            goodsCart.setGoods(goods);
            goodsCart.setParentCategoryId(categoryDao.getOne(goods.getCategoryId()).getParentId());
            EvaluatePO evaluate = evaluateDao.getByCartId(goodsCart.getId());
            if (evaluate != null) {
                goodsCart.setEvaluateId(evaluate.getId());
            }
        }
    }

    @Override
    public List<GoodsCartVO> getByUser(int userId, int status, int pageSize, int pageIndex) {
        Page<GoodsCartPO> goodsCartsPage = goodsCartDao.getByUserIdAndStatus(userId, status, PageRequest.of(pageIndex * pageSize, pageSize));
        List<GoodsCartVO> goodsCarts = listCopy.listBuild(goodsCartsPage.getContent(), GoodsCartVO.class);
        addAttribute(goodsCarts);
        return goodsCarts;
    }

    @Override
    public void deleteById(int cartId) {
        goodsCartDao.deleteById(cartId);
    }

    @Override
    public void updateById(int cartId, int number) {
        GoodsCartPO goodsCart = goodsCartDao.getOne(cartId);
        goodsCart.setNumber(number);
        goodsCart.setStatus(1);
        goodsCartDao.save(goodsCart);
    }

    @Override
    public List<GoodsCartVO> getByInfo(int userId, int goodsId, int status) {
        List<GoodsCartPO> goodsCartPos = goodsCartDao.getByUserIdAndStatusAndGoodsId(userId, goodsId, status);
        List<GoodsCartVO> goodsCartVos = listCopy.listBuild(goodsCartPos, GoodsCartVO.class);
        addAttribute(goodsCartVos);
        return goodsCartVos;
    }

    @Override
    public void insertCartInfo(int userId, int goodsId, int number, int status) {
        GoodsCartPO goodsCart = new GoodsCartPO();
        goodsCart.setUserId(userId);
        goodsCart.setGoodsId(goodsId);
        goodsCart.setNumber(number);
        goodsCart.setStatus(status);
        goodsCart.setCreateTime(new Date());
        goodsCartDao.save(goodsCart);
    }

    @Override
    public void addCartCountById(int cartId) {
        GoodsCartPO goodsCart = goodsCartDao.getOne(cartId);
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
    public List<GoodsCartVO> getPurchasedGoodByUserId(int userId, int pageSize, int pageIndex) {
        Page<GoodsCartPO> goodsCartsPage = goodsCartDao.getByUserIdAndStatus(userId, 1, PageRequest.of(pageIndex * pageSize, pageSize));
        List<GoodsCartVO> goodsCarts = listCopy.listBuild(goodsCartsPage.getContent(), GoodsCartVO.class);
        addAttribute(goodsCarts);
        return goodsCarts;
    }
}
