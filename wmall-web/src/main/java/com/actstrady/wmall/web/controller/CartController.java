package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.po.UserPO;
import com.actstrady.wmall.service.GoodsCartService;
import com.actstrady.wmall.utils.Constant;
import com.actstrady.wmall.utils.Result;
import com.actstrady.wmall.vo.GoodsCartVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {
    private final GoodsCartService goodsCartService;
    private final Result result;

    @Autowired
    public CartController(GoodsCartService goodsCartService, Result result) {
        this.goodsCartService = goodsCartService;
        this.result = result;
    }

    /**
     * 购物车列表页
     *
     * @param pageSize  条数
     * @param pageIndex 第几页
     * @return 购物车列表数据
     */
    @GetMapping("cartView/{pageSize}/{pageIndex}")
    public Result cartView(@PathVariable("pageSize") int pageSize, @PathVariable("pageIndex") int pageIndex, HttpSession httpSession) {
        UserPO user = (UserPO) httpSession.getAttribute("user");
        int userId = user.getId();
        // 获取购物车列表
        List<GoodsCartVO> carts = goodsCartService.getByUser(userId, 0, pageSize, pageIndex);
        result.setCode(Constant.ZERO);
        result.setStatus(Constant.ZERO_SHORT);
        result.setData(JSON.toJSONString(carts));
        return result;
    }

    /**
     * 删购物车信息
     *
     * @param cartIds 购物车id
     */
    @PostMapping("deleteCartByIds/{cartIds}")
    public void deleteCartByIds(@PathVariable("cartIds") String cartIds) {
        String[] strs = cartIds.split(",");
        for (String str : strs) {
            goodsCartService.deleteById(Integer.parseInt(str));
        }
    }

    /**
     * 购物车购买
     *
     * @param arr 购买参数
     */
    @PostMapping("buyGoods")
    public void buyGoods(@RequestBody List<GoodsCartVO> arr) {
        for (GoodsCartVO cart : arr) {
            goodsCartService.updateById(cart.getId(), cart.getNumber());
        }
    }

    /**
     * 添加购物车
     *
     * @param cart        购物信息
     * @param httpSession httpSession
     * @return 成功否
     */
    @PostMapping("addCart")
    public boolean addCart(@RequestBody GoodsCartVO cart, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            UserPO user = (UserPO) httpSession.getAttribute("user");
            int userId = user.getId();
            // 是否存在
            List<GoodsCartVO> isExits = goodsCartService.getByInfo(userId, cart.getGoodsId(), 0);
            if (isExits == null || isExits.size() == 0) {
                goodsCartService.insertCartInfo(userId, cart.getGoodsId(), cart.getNumber(), 0);
            } else {
                goodsCartService.addCartCountById(isExits.get(0).getId());
            }
            return true;
        } else {
            return false;
        }

    }

    /**
     * 直接购买
     *
     * @param cart 购买信息
     * @param httpSession httpSession
     * @return 购买成功否
     */
    @PostMapping("buyGoodQuick")
    public boolean buyGoodQuick(@RequestBody GoodsCartVO cart, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            UserPO user = (UserPO) httpSession.getAttribute("user");
            int userId = user.getId();
            goodsCartService.insertCartInfo(userId, cart.getGoodsId(), cart.getNumber(), 1);
            return true;
        } else {
            return false;
        }
    }
}
