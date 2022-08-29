package com.imooc.mall.service;

import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

public interface ICategoryService {

    ResponseVo<List<CategoryVo>> selectAll();

    Set<Integer> findSubCategoryId(Integer id);
}
