package com.redrain.controller;

import com.redrain.entity.Article;
import com.redrain.service.ArticleService;
import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description Articlecontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

	@RequestMapping("listPage")
    public String listPage() {
        return "article/list";
    }

	@RequestMapping("addPage")
    public String addPage() {
        return "article/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "article/edit";
    }

	@RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(Article article) {
        return articleService.getList(article);
    }

	@RequestMapping("/add")
    @ResponseBody
    public ReturnData add(Article article, Long userId) {
        return articleService.add(article);
    }

	@RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(Article article) {
        return articleService.delete(article);
    }

	@RequestMapping("/update")
    @ResponseBody
    public ReturnData update(Article article) {
        return articleService.update(article);
    }

	@RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(Article article) {
        return articleService.updateState(article);
    }

}

