package com.actstrady.wmall.web.controller;

import com.iflysse.pojo.Evaluate;
import com.iflysse.service.CategoryService;
import com.iflysse.service.EvaluateService;
import com.iflysse.service.GoodsCartService;
import com.iflysse.service.GoodsService;
import com.iflysse.viewmodel.CategoryViewModel.ParentCategory;
import com.iflysse.viewmodel.EvaluateViewModel.EvaluateList;
import com.iflysse.viewmodel.GoodsCartModel.GoodsCartList;
import com.iflysse.viewmodel.UserViewModel.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GoodsCartService goodsCartService;

    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 购物车列表页
     * @param model
     * @param pageSize
     * @param pageIndex
     * @return
     */

    @RequestMapping("/order/orderView/{pageSize}/{pageIndex}")
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
