package com.leyou.item.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品规格参数模板
 */
@Table(name = "tb_specification")
public class Specification {

    @Id
    private Long categoryId;
    private String specifications;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "categoryId=" + categoryId +
                ", specifications='" + specifications + '\'' +
                '}';
    }
}