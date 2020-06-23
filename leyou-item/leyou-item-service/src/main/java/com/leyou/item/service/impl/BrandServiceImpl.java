package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import com.leyou.parameter.pojo.BrandQueryByPageParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-08-07 19:16
 * Feature: 分类的业务层
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 1 分页查询
     *
     * @param brandQueryByPageParameter
     * @return
     */
    @Override
    public PageResult<Brand> queryBrandByPage(BrandQueryByPageParameter brandQueryByPageParameter) {

        /**
         * 1.分页
         */
        PageHelper.startPage(brandQueryByPageParameter.getPage(), brandQueryByPageParameter.getRows());

        /**
         *  2.排序
         */
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(brandQueryByPageParameter.getSortBy())) {
            example.setOrderByClause(brandQueryByPageParameter.getSortBy() + (brandQueryByPageParameter.getDesc() ? " DESC" : " ASC"));
        }
        /**
         * 3.查询
         */
        if (StringUtils.isNotBlank(brandQueryByPageParameter.getKey())) {
            example.createCriteria().orLike("name", brandQueryByPageParameter.getKey() + "%").orEqualTo("letter", brandQueryByPageParameter.getKey().toUpperCase());
        }
        List<Brand> list = this.brandMapper.selectByExample(example);

        /**
         * 4.创建PageInfo
         */
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        /**
         * 5.返回分页结果
         */
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 2 品牌新增
     *
     * @param brand
     * @param categories
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBrand(Brand brand, List<Long> categories) {
        //System.out.println(brand);
        //  1 新增品牌信息
        this.brandMapper.insertSelective(brand);
        //  2 新增品牌和分类中间表，一个品牌属于多个分类！
        for (Long cid : categories) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }

    /**
     * 3 品牌更新
     *
     * @param brand
     * @param categories
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBrand(Brand brand, List<Long> categories) {
        // todo 删除原来的数据?  是中间表的数据！
        deleteByBrandIdInCategoryBrand(brand.getId());

        // 修改品牌信息
        this.brandMapper.updateByPrimaryKeySelective(brand);

        //维护品牌和分类中间表，新的插入！
        for (Long cid : categories) {
            //System.out.println("cid:"+cid+",bid:"+brand.getId());
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }

    /**
     * 4 品牌删除
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBrand(Long id) {
        //删除品牌信息
        this.brandMapper.deleteByPrimaryKey(id);

        //维护中间表
        this.brandMapper.deleteByBrandIdInCategoryBrand(id);
    }


    /**
     * 5  删除中间表中的数据
     *
     * @param bid
     */
    @Override
    public void deleteByBrandIdInCategoryBrand(Long bid) {
        this.brandMapper.deleteByBrandIdInCategoryBrand(bid);
    }

    /**
     * 6 根据category id查询brand
     *
     * @param cid
     * @return
     */
    @Override
    public List<Brand> queryBrandByCategoryId(Long cid) {

        return this.brandMapper.queryBrandByCategoryId(cid);
    }

    /**
     * 7 根据品牌id集合查询品牌信息
     *
     * @param ids
     * @return
     */
    @Override
    public List<Brand> queryBrandByBrandIds(List<Long> ids) {
        return this.brandMapper.selectByIdList(ids);
    }

}
