package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.po.Evaluate;
import com.actstrady.wmall.po.User;
import com.actstrady.wmall.service.CategoryService;
import com.actstrady.wmall.service.EvaluateService;
import com.actstrady.wmall.service.GoodsCartService;
import com.actstrady.wmall.service.GoodsService;
import com.actstrady.wmall.utils.Constant;
import com.actstrady.wmall.utils.Result;
import com.actstrady.wmall.vo.EvaluateList;
import com.actstrady.wmall.vo.GoodsCartList;
import com.alibaba.fastjson.JSON;
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
    private final Result result;

    public OrderController(CategoryService categoryService, GoodsCartService goodsCartService,
                           EvaluateService evaluateService, GoodsService goodsService, Result result) {
        this.categoryService = categoryService;
        this.goodsCartService = goodsCartService;
        this.evaluateService = evaluateService;
        this.goodsService = goodsService;
        this.result = result;
    }

    /**
     * 订单列表页
     *
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @GetMapping("orderView/{pageSize}/{pageIndex}")
    public Result orderView(HttpSession httpSession,
                            @PathVariable("pageSize") int pageSize,
                            @PathVariable("pageIndex") int pageIndex) {
        User user = (User) httpSession.getAttribute("user");
        int userId = user.getId();
        // 已购列表
        List<GoodsCartList> carts = goodsCartService.getPurchasedGoodByUserId(userId, pageSize, pageIndex);
        result.setCode(Constant.ZERO);
        result.setStatus(Constant.ZERO_SHORT);
        result.setData(JSON.toJSONString(carts));
        return result;
    }

    /**
     * 添加评价
     *
     * @param data
     * @param httpSession
     * @return
     */
    @GetMapping("addEvaluation")
    public boolean addEvaluation(@RequestBody Evaluate data, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            int userId = ((User) httpSession.getAttribute("user")).getId();
            data.setUserId(userId);
            Evaluate evaluate = evaluateService.insertEvaluateInfo(data);
            if (evaluate == null) {
                return false;
            } else {
                // 更新评价
                goodsService.updateGradeById(data.getGoodsId(), data.getGrade());
                return true;
            }
        }
        return false;
    }

    @GetMapping("getByCartId")
    public EvaluateList getByCartId(@RequestBody Evaluate data) {
        EvaluateList result = evaluateService.getByCartId(data.getCartId());
        String i = result.getGrade() + result.getComment();
        return result;
    }
}
