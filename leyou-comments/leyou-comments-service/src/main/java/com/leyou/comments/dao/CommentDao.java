package com.leyou.comments.dao;


import com.leyou.comments.pojo.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * @Author: 98050
 * @Time: 2018-11-26 20:51
 * @Feature:
 */
public interface CommentDao extends MongoRepository<Review,String> {

    /**
     * 1 分页查询
     * @param spuId  华为手机，具体的型号不过是他的子集合
     * @param pageable
     * @return
     */
    Page<Review> findReviewBySpuid(String spuId, Pageable pageable);
}
