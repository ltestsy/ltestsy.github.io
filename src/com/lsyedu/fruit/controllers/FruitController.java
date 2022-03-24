package com.lsyedu.fruit.controllers;

import com.lsyedu.fruit.service.FruitService;
import com.lsyedu.fruit.pojo.Fruit;
import com.lsyedu.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lsy
 * @version 1.0
 */
public class FruitController {
    private FruitService fruitService = null;

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        fruitService.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        //resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    private String edit(Integer fid, HttpServletRequest req) {
        if(fid != null){
            Fruit fruit = fruitService.getFruitByFid(fid);
            req.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String del(Integer fid) {
        if(fid != null) {
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitService.addFruit(fruit);
        return "redirect:fruit.do";
    }

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest req) {
        HttpSession session = req.getSession();
        if(pageNo == null) {
            pageNo = 1;
        }
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            pageNo = 1;
            if(StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj != null) {
                keyword = (String)keywordObj;
            } else {
                keyword = "";
            }
        }
        session.setAttribute("pageNo", pageNo);
        List<Fruit> fruitList = fruitService.getFruitList(keyword, pageNo);
        session.setAttribute("fruitList", fruitList);
        Integer pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("pageCount", pageCount);
        return "index";
    }
}
