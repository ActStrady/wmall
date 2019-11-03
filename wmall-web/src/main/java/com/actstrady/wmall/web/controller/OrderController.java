package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.po.Evaluate;
import com.actstrady.wmall.service.CategoryService;
import com.actstrady.wmall.service.EvaluateService;
import com.actstrady.wmall.service.GoodsCartService;
import com.actstrady.wmall.service.GoodsService;
import com.actstrady.wmall.vo.EvaluateList;
import com.actstrady.wmall.vo.GoodsCartList;
import com.actstrady.wmall.vo.ParentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    private final CategoryService categoryService;
    private final GoodsCartService goodsCartService;
    private final EvaluateService evaluateService;
    private final GoodsService goodsService;

    public OrderController(CategoryService categoryService, GoodsCartService goodsCartService, EvaluateService evaluateService, GoodsService goodsService) {
        this.categoryService = categoryService;
        this.goodsCartService = goodsCartService;
        this.evaluateService = evaluateService;
        this.goodsService = goodsService;
    }

    /**
     * 购物车列表页
     * @param model
     * @param pageSize
     * @param pageIndex
     * @return
     */

    @RequestMapping("orderView/{pageSize}/{pageIndex}")
    public String OrderView(Model model, @PathVariable("pageSize") int pageSize, @PathVariable("pageIndex") int pageIndex, HttpSession httpSession){
        UserList user=(UserList)httpSession.getAttribute("userList");
        int userId=user.getUserId();
        List<GoodsCartList> carts = goodsCartService.getPurchasedGoodByUserId(userId,pageSize,pageIndex);
        model.addAttribute("carts", carts);
        List<ParentCategory> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("userList",user);
        return "order";
    }

    @RequestMapping("/order/addEvaluation")
    @ResponseBody
    public boolean  AddEvaluation(@RequestBody Evaluate data, HttpSession httpSession) {
        if (httpSession.getAttribute("userList")!=null) {
            int userId=((UserList)httpSession.getAttribute("userList")).getUserId();
            data.setUserId(userId);
            evaluateService.insertEvaluateInfo(data);
            goodsService.updateGradeById(data.getGoodsId(),data.getGrade());
            return true;
        }
        else
        {
            return false;
        }
    }

    @RequestMapping("/order/getByCartId")
    @ResponseBody
    public EvaluateList GetByCartId(@RequestBody Evaluate data) {
        EvaluateList result=evaluateService.getByCartId (data.getCartId());
        String i=result.getGrade()+result.getComment();
        return result;
    }


}
