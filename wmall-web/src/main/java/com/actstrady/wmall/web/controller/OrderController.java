package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.po.EvaluatePO;
import com.actstrady.wmall.po.UserPO;
import com.actstrady.wmall.service.EvaluateService;
import com.actstrady.wmall.service.GoodsCartService;
import com.actstrady.wmall.service.GoodsService;
import com.actstrady.wmall.utils.Constant;
import com.actstrady.wmall.utils.Result;
import com.actstrady.wmall.vo.EvaluateVO;
import com.actstrady.wmall.vo.GoodsCartVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    private final GoodsCartService goodsCartService;
    private final EvaluateService evaluateService;
    private final GoodsService goodsService;
    private final Result result;

    @Autowired
    public OrderController(GoodsCartService goodsCartService, EvaluateService evaluateService,
                           GoodsService goodsService, Result result) {
        this.goodsCartService = goodsCartService;
        this.evaluateService = evaluateService;
        this.goodsService = goodsService;
        this.result = result;
    }

    /**
     * 订单列表页
     *
     * @param pageSize 条数
     * @param pageIndex 页
     * @return 我的订单
     */
    @GetMapping("orderView/{pageSize}/{pageIndex}")
    public Result orderView(HttpSession httpSession,
                            @PathVariable("pageSize") int pageSize,
                            @PathVariable("pageIndex") int pageIndex) {
        UserPO user = (UserPO) httpSession.getAttribute("user");
        int userId = user.getId();
        // 已购列表
        List<GoodsCartVO> carts = goodsCartService.getPurchasedGoodByUserId(userId, pageSize, pageIndex);
        result.setCode(Constant.ZERO);
        result.setStatus(Constant.ZERO_SHORT);
        result.setData(JSON.toJSONString(JSON.toJSON(carts)));
        return result;
    }

    /**
     * 添加评价
     *
     * @param data 评价信息
     * @param httpSession httpSession
     * @return 成功否
     */
    @PostMapping("addEvaluation")
    public boolean addEvaluation(@RequestBody EvaluatePO data, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            int userId = ((UserPO) httpSession.getAttribute("user")).getId();
            data.setUserId(userId);
            EvaluatePO evaluatePO = evaluateService.insertEvaluateInfo(data);
            if (evaluatePO == null) {
                return false;
            } else {
                // 更新评价
                goodsService.updateGradeById(data.getGoodsId(), data.getGrade());
                return true;
            }
        }
        return false;
    }

    /**
     * 获取订单评价
     * @param data 评价
     * @return 评价
     */
    @PostMapping("getByCartId")
    public EvaluateVO getByCartId(@RequestBody EvaluatePO data) {
        return evaluateService.getByCartId(data.getCartId());
    }
}
