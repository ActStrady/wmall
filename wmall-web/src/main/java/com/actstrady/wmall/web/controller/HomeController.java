package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.service.*;
import com.actstrady.wmall.vo.EvaluateList;
import com.actstrady.wmall.vo.Goods4List;
import com.actstrady.wmall.vo.ParentCategory;
import com.actstrady.wmall.vo.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private HotGoodsService hotGoodsService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private SimilarPurchasedService similarGoodsService;
    /**
     * 系统首页
     * @param model ViewModel
     * @return
     */
    @RequestMapping("/home")
    public String index(Model model, HttpSession httpSession) {
        List<ParentCategory> categories = categoryService.getCategories();
        List<Goods4List> goods = goodsService.getAll(9, 0);
        List<Goods4List> hotGoods = hotGoodsService.getTop();
        List<Goods4List> newsGoods = goodsService.getNewsByTime();
        model.addAttribute("categories", categories);
        model.addAttribute("goods", goods);
        model.addAttribute("hotGoods", hotGoods);
        model.addAttribute("newsGoods", newsGoods);
        List<Goods4List> recommendGoods=new ArrayList<>(0);
        UserList user=new UserList();
        if (httpSession.getAttribute("userList")!=null) {
            user=(UserList)httpSession.getAttribute("userList");
            int userId=user.getUserId();
            recommendGoods=recommendService.getByUserId(userId);
        }
        model.addAttribute("userList", user);
        model.addAttribute("recommendGoods", recommendGoods);

        return "index";
    }

    /**
     *
     * @param model
     * @param categoryId
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping("/home/product/{cId}/{pageSize}/{pageIndex}")
    public String Product(Model model, @PathVariable("cId") int categoryId,
                          @PathVariable("pageSize") int pageSize, @PathVariable("pageIndex") int pageIndex,
                          HttpSession httpSession){
        List<Goods4List> goods = goodsService.getByCategory(categoryId, pageSize, pageIndex);
        model.addAttribute("goods", goods);
        List<ParentCategory> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        UserList user=new UserList();
        if (httpSession.getAttribute("userList")!=null) {
            user=((UserList)httpSession.getAttribute("userList"));
        }
        model.addAttribute("userList", user);
        return "goods";
    }
    @RequestMapping("/home/product/search/{content}/{pageSize}/{pageIndex}")
    public String ProductSearch(Model model, @PathVariable("content") String content,
                                @PathVariable("pageSize") int pageSize, @PathVariable("pageIndex") int pageIndex, HttpSession httpSession){
        List<Goods4List> goods = goodsService.getByName(content,pageSize,pageIndex);
        model.addAttribute("goods", goods);
        List<ParentCategory> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        UserList user=new UserList();
        if (httpSession.getAttribute("userList")!=null) {
            user=((UserList)httpSession.getAttribute("userList"));
        }
        model.addAttribute("userList", user);
        return "goods";
    }

    /**
     *物品详情页
     * @param model
     * @param goodsId
     * @return
     */
    @RequestMapping("/home/productView/{gId}")
    public String ProductView(Model model, @PathVariable("gId") int goodsId, HttpSession httpSession){
        Goods4List goods = goodsService.getById(goodsId);
        model.addAttribute("goods", goods);
        List<EvaluateList> evaluates = evaluateService.getByGood(goodsId,10,0);
        model.addAttribute("evaluates", evaluates);
        List<Goods4List> similarGoods = similarGoodsService.getByGoodsId(goodsId);
        model.addAttribute("similarGoods", similarGoods);

        List<ParentCategory> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        UserList user=new UserList();
        if (httpSession.getAttribute("userList")!=null) {
            user=((UserList)httpSession.getAttribute("userList"));
        }
        model.addAttribute("userList", user);

        return "detail";
    }

}
