package com.redrain.mapper;

import com.redrain.entity.ArticleComment;

import java.util.List;

/**
 * @author redrain
 * @Description ArticleCommentMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface ArticleCommentMapper {

	long getCount(ArticleComment articleComment);

	List<ArticleComment> getList(ArticleComment articleComment);

	int add(ArticleComment articleComment);

	int delete(ArticleComment articleComment);

	int update(ArticleComment articleComment);

	int updateState(ArticleComment articleComment);

}

