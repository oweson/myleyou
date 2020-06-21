package com.leyou.item.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_category")
/**
 * @author li
 * @time 2018/8/7
 * @feature: 商品分类对应的实体
 */
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** 1 商品id*/
    private Long id;
    /**
     * 2 商品名字
     */
    private String name;
    /**
     * 3 父级别分类
     */

    private Long parentId;
    /**
     * 4 是父分类吗
     */
    private Boolean isParent;
    /**
     * 5 排序指数，越小越靠前
     */
    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", isParent=" + isParent +
                ", sort=" + sort +
                '}';
    }
}