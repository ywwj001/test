package com.redrain.mapper;

import com.redrain.entity.Article;

import java.util.List;

/**
 * @author redrain
 * @Description ArticleMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface ArticleMapper {

	long getCount(Article article);

	List<Article> getList(Article article);

	int add(Article article);

	int delete(Article article);

	int update(Article article);

	int updateState(Article article);

}

