package com.redrain.service;

import com.redrain.entity.ArticleComment;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description ArticleCommentservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface ArticleCommentService {

	ReturnDataForLayui getList(ArticleComment articleComment);

	ReturnData add(ArticleComment articleComment);

	ReturnData delete(ArticleComment articleComment);

	ReturnData update(ArticleComment articleComment);

	ReturnData updateState(ArticleComment articleComment);

}

