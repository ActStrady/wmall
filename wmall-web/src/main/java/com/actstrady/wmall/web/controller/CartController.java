package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.service.CategoryService;
import com.actstrady.wmall.service.GoodsCartService;
import com.actstrady.wmall.vo.GoodsCartList;
import com.actstrady.wmall.vo.ParentCategory;
import com.actstrady.wmall.vo.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GoodsCartService goodsCartService;

    /**
     * 购物车列表页
     * @param model
     * @param pageSize
     * @param pageIndex
     * @return
     */

    @RequestMapping("/cart/cartView/{pageSize}/{pageIndex}")
    public String CartView(Model model, @PathVariable("pageSize") int pageSize, @PathVariable("pageIndex") int pageIndex, HttpSession httpSession){
        UserList user=(UserList)httpSession.getAttribute("userList");
        int userId=user.getUserId();
        model.addAttribute("userList",user);
        List<GoodsCartList> carts = goodsCartService.getByUser(userId,0,pageSize,pageIndex);
        model.addAttribute("carts", carts);
        List<ParentCategory> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "cart";
    }

    @RequestMapping("/cart/deleteCartByIds/{cartIds}")
    public void  DeleteCartByIds(@PathVariable("cartIds") String cartIds) {

        String items = cartIds;
        List<String> delList = new ArrayList<String>();
        String[] strs = items.split(",");
        for (String str : strs) {
            goodsCartService.deleteById(Integer.parseInt(str));
        }
    }

    @ResponseBody
    @RequestMapping("/cart/buyGoods")
    public void  BuyGoods(@RequestBody List<GoodsCartList> arr) {
        for (GoodsCartList cart : arr) {
            goodsCartService.updateById(cart.getId(),cart.getNumber());
        }
    }

    @RequestMapping("/cart/addCart")
    @ResponseBody
    public boolean  AddCart(@RequestBody GoodsCartList cart, HttpSession httpSession) {
        if (httpSession.getAttribute("userList")!=null)
        {
            UserList user=(UserList)httpSession.getAttribute("userList");
            int userId=user.getUserId();
            List<GoodsCartList> isExits=goodsCartService.getByInfo(userId,cart.getGoodsId(),0);
            if(isExits==null || isExits.size()==0){
                goodsCartService.insertCartInfo(userId,cart.getGoodsId(),cart.getNumber(),0);
            }
            else
            {
                goodsCartService.addCartCountById(isExits.get(0).getId());
            }
            return true;
        }
        else
         {
             return false;
        }

    }
    @RequestMapping("/cart/buyGoodQuick")
    @ResponseBody
    public boolean  buyGoodQuick(@RequestBody GoodsCartList cart, HttpSession httpSession) {
        if (httpSession.getAttribute("userList")!=null) {
            UserList user=(UserList)httpSession.getAttribute("userList");
            int userId=user.getUserId();
            goodsCartService.insertCartInfo(userId, cart.getGoodsId(), cart.getNumber(), 1);
            return true;
        }
        else
        {
            return false;
        }
    }
}
