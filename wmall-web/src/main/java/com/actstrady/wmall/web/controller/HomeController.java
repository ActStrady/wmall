package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.po.UserPO;
import com.actstrady.wmall.service.*;
import com.actstrady.wmall.utils.Constant;
import com.actstrady.wmall.utils.Result;
import com.actstrady.wmall.vo.EvaluateVO;
import com.actstrady.wmall.vo.GoodsVO;
import com.actstrady.wmall.vo.ParentCategory;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("home")
public class HomeController {
    private final CategoryService categoryService;
    private final GoodsService goodsService;
    private final EvaluateService evaluateService;
    private final HotGoodsService hotGoodsService;
    private final RecommendService recommendService;
    private final SimilarPurchasedService similarGoodsService;
    // 封装结果集
    private final Result result;

    @Autowired
    public HomeController(CategoryService categoryService, GoodsService goodsService,
                          EvaluateService evaluateService, HotGoodsService hotGoodsService,
                          RecommendService recommendService, SimilarPurchasedService similarGoodsService,
                          Result result) {
        this.categoryService = categoryService;
        this.goodsService = goodsService;
        this.evaluateService = evaluateService;
        this.hotGoodsService = hotGoodsService;
        this.recommendService = recommendService;
        this.similarGoodsService = similarGoodsService;
        this.result = result;
    }

    /**
     * 系统首页
     *
     * @param model ViewModel
     * @return
     */
    @GetMapping("data")
    public Result index(Model model, HttpSession httpSession) {
        // 给前端返回的数据
        Map<String, Object> homeMap = new HashMap<>(50);
        // 分页查询商品
        List<GoodsVO> goods = goodsService.getAll(9, 0);
        // 获取热门商品
        List<GoodsVO> hotGoods = hotGoodsService.getTop();
        // 获取商品列表
        List<GoodsVO> newsGoods = goodsService.getNewsByTime();

        // 添加结果
        homeMap.put("goods", goods);
        homeMap.put("hotGoods", hotGoods);
        homeMap.put("newsGoods", newsGoods);

        // 通过用户来推荐
        UserPO user = new UserPO();
        if (httpSession.getAttribute(Constant.USER) != null) {
            user = (UserPO) httpSession.getAttribute(Constant.USER);
            int userId = user.getId();
            // 个性化推荐
            List<GoodsVO> recommendGoods = recommendService.getByUserId(userId);
            homeMap.put("user", user);
            homeMap.put("recommendGoods", recommendGoods);
        }
        result.setCode(Constant.ZERO);
        result.setStatus(Constant.ZERO_SHORT);
        result.setData(JSON.toJSONString(homeMap));
        return result;
    }

    @GetMapping("head")
    public Result head() {
        // 菜单列表
        List<ParentCategory> categories = categoryService.getCategories();
        result.setCode(Constant.ZERO);
        result.setStatus(Constant.ZERO_SHORT);
        result.setData(JSON.toJSONString(categories));
        return result;
    }
    /**
     * @param model
     * @param categoryId
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @GetMapping("product/{cId}/{pageSize}/{pageIndex}")
    public Result product(Model model, HttpSession httpSession,
                          @PathVariable("cId") int categoryId,
                          @PathVariable("pageSize") int pageSize,
                          @PathVariable("pageIndex") int pageIndex) {
        // 通过分类获取商品
        List<GoodsVO> goods = goodsService.getByCategory(categoryId, pageSize, pageIndex);
        result.setCode(Constant.ZERO);
        result.setStatus(Constant.ZERO_SHORT);
        result.setData(JSON.toJSONString(goods));
        return result;
    }

    /**
     * 搜索
     *
     * @param model
     * @param content
     * @param pageSize
     * @param pageIndex
     * @param httpSession
     * @return
     */
    @GetMapping("product/search/{content}/{pageSize}/{pageIndex}")
    public Result productSearch(Model model, HttpSession httpSession,
                                @PathVariable("content") String content,
                                @PathVariable("pageSize") int pageSize,
                                @PathVariable("pageIndex") int pageIndex) {
        List<GoodsVO> goods = goodsService.getByName(content, pageSize, pageIndex);
        result.setCode(Constant.ZERO);
        result.setStatus(Constant.ZERO_SHORT);
        result.setData(JSON.toJSONString(goods));
        return result;
    }

    /**
     * 物品详情页
     *
     * @param goodsId
     * @return
     */
    @GetMapping("productView/{gId}")
    public Result productView(@PathVariable("gId") int goodsId) {
        Map<String, Object> productMap = new HashMap<>();
        GoodsVO goods = goodsService.getById(goodsId);
        // 评价信息
        List<EvaluateVO> evaluates = evaluateService.getByGood(goodsId, 10, 0);

        // 同类型商品
        List<GoodsVO> similarGoods = similarGoodsService.getByGoodsId(goodsId);
        productMap.put("goods", goods);
        productMap.put("evaluates", evaluates);
        productMap.put("similarGoods", similarGoods);
        result.setCode(Constant.ZERO);
        result.setStatus(Constant.ZERO_SHORT);
        // JSON.toJSON解决嵌套问题
        result.setData(JSON.toJSONString(JSON.toJSON(productMap)));
        return result;
    }
}
