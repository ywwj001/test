package com.redrain.service;

import com.redrain.entity.Article;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description Articleservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface ArticleService {

	ReturnDataForLayui getList(Article article);

	ReturnData add(Article article);

	ReturnData delete(Article article);

	ReturnData update(Article article);

	ReturnData updateState(Article article);

}

