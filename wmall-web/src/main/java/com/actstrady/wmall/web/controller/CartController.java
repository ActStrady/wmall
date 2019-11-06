package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.po.User;
import com.actstrady.wmall.service.GoodsCartService;
import com.actstrady.wmall.utils.Constant;
import com.actstrady.wmall.utils.Result;
import com.actstrady.wmall.vo.GoodsCartList;
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
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @GetMapping("cartView/{pageSize}/{pageIndex}")
    public Result cartView(@PathVariable("pageSize") int pageSize, @PathVariable("pageIndex") int pageIndex, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        int userId = user.getId();
        // 获取购物车列表
        List<GoodsCartList> carts = goodsCartService.getByUser(userId, 0, pageSize, pageIndex);
        result.setCode(Constant.ZERO);
        result.setStatus(Constant.ZERO_SHORT);
        result.setData(JSON.toJSONString(carts));
        return result;
    }

    /**
     * 删购物车
     * @param cartIds
     */
    @GetMapping("deleteCartByIds/{cartIds}")
    public void deleteCartByIds(@PathVariable("cartIds") String cartIds) {
        String[] strs = cartIds.split(",");
        for (String str : strs) {
            goodsCartService.deleteById(Integer.parseInt(str));
        }
    }

    @GetMapping("buyGoods")
    public void buyGoods(@RequestBody List<GoodsCartList> arr) {
        for (GoodsCartList cart : arr) {
            goodsCartService.updateById(cart.getId(), cart.getNumber());
        }
    }

    /**
     *  添加购物车
     * @param cart
     * @param httpSession
     * @return
     */
    @PostMapping("addCart")
    public boolean addCart(@RequestBody GoodsCartList cart, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            User user = (User) httpSession.getAttribute("user");
            int userId = user.getId();
            // 是否存在
            List<GoodsCartList> isExits = goodsCartService.getByInfo(userId, cart.getGoodsId(), 0);
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

    @PostMapping("buyGoodQuick")
    public boolean buyGoodQuick(@RequestBody GoodsCartList cart, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            User user = (User) httpSession.getAttribute("user");
            int userId = user.getId();
            goodsCartService.insertCartInfo(userId, cart.getGoodsId(), cart.getNumber(), 1);
            return true;
        } else {
            return false;
        }
    }
}
