package com.imooc.mall.service.impl;

import com.imooc.mall.dao.CategoryMapper;
import com.imooc.mall.pojo.Category;
import com.imooc.mall.service.ICategoryService;
import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        // 根目录列表
        List<CategoryVo> categoryVoList = new ArrayList<>();
        List<Category> categories = categoryMapper.selectAll();
        // 查根目录（parent_id=0）
        for (Category category : categories) {
            if (category.getParentId().equals(0)) {
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVo);
                categoryVoList.add(categoryVo);
            }
        }
        categoryVoList.sort((CategoryVo c1, CategoryVo c2) -> c2.getSortOrder().compareTo(c1.getSortOrder()));
        // 查子目录
        findSubCategory(categories, categoryVoList);
        
        return ResponseVo.success(categoryVoList);
    }

    @Override
    public Set<Integer> findSubCategoryId(Integer id) {
        List<Category> categories = categoryMapper.selectAll();
        Set<Integer> subCategoryIdSet = new HashSet<>();
        findSubCategoryId(id, categories, subCategoryIdSet);
        return subCategoryIdSet;
    }

    private void findSubCategoryId(Integer id, List<Category> categories, Set<Integer> subCategoryIdSet) {
        for (Category category : categories) {
            if (category.getParentId().equals(id)) {
                subCategoryIdSet.add(category.getId());
                findSubCategoryId(category.getId(), categories, subCategoryIdSet);
            }
        }
    }

    private void findSubCategory(List<Category> categories, List<CategoryVo> categoryVoList) {
        // 遍历根目录
        for (CategoryVo categoryVo : categoryVoList) {
            List<CategoryVo> subCategoryVoList = new ArrayList<>();
            for (Category category : categories) {
                if (category.getParentId().equals(categoryVo.getId())) {
                    CategoryVo subCategoryVo = new CategoryVo();
                    BeanUtils.copyProperties(category, subCategoryVo);
                    subCategoryVoList.add(subCategoryVo);
                }
            }
            subCategoryVoList.sort((CategoryVo c1, CategoryVo c2) -> c2.getSortOrder().compareTo(c1.getSortOrder()));
            categoryVo.setSubCategories(subCategoryVoList);
            findSubCategory(categories, subCategoryVoList);
        }
    }
}
