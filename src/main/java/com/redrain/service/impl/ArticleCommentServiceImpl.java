package com.redrain.service.impl;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redrain.entity.ArticleComment;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.redrain.mapper.ArticleCommentMapper;

import com.redrain.service.ArticleCommentService;

import com.redrain.common.utils.UpdateOrInsertResultDeal;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author redrain
 * @Description ArticleCommentservice实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(ArticleComment articleComment) {
        PageHelper.startPage(articleComment.getPage(), articleComment.getLimit());
        List<ArticleComment> list = articleCommentMapper.getList(articleComment);
        PageInfo<ArticleComment> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(ArticleComment articleComment) {
        int i = articleCommentMapper.add(articleComment);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(ArticleComment articleComment) {
        int i = articleCommentMapper.delete(articleComment);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(ArticleComment articleComment) {
        int i = articleCommentMapper.update(articleComment);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(ArticleComment articleComment) {
        int i = articleCommentMapper.updateState(articleComment);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

}

