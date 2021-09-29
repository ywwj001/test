package com.redrain.controller;

import com.redrain.entity.ArticleComment;
import com.redrain.entity.User;
import com.redrain.service.ArticleCommentService;
import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import com.redrain.service.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description ArticleCommentcontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/articleComment")
public class ArticleCommentController {

    @Resource
    private UserUtils userUtils;

    @Autowired
    private ArticleCommentService articleCommentService;

    @RequestMapping("listPage")
    public String listPage() {
        return "articleComment/list";
    }

    @RequestMapping("addPage")
    public String addPage() {
        return "articleComment/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "articleComment/edit";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(ArticleComment articleComment) {
        return articleCommentService.getList(articleComment);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnData add(ArticleComment articleComment, HttpServletRequest request) {
        User user = new User();
        ReturnData returnData = userUtils.setUserId(request, user);
        if (!returnData.isSuccess()) {
            return returnData;
        }
        articleComment.setUid(user.getId());
        return articleCommentService.add(articleComment);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(ArticleComment articleComment) {
        return articleCommentService.delete(articleComment);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(ArticleComment articleComment) {
        return articleCommentService.update(articleComment);
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(ArticleComment articleComment) {
        return articleCommentService.updateState(articleComment);
    }

}

