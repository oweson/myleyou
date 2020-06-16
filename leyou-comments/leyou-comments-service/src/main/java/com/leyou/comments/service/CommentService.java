package com.leyou.comments.service;

import com.leyou.comments.bo.CommentRequestParam;
import com.leyou.comments.pojo.Review;
import org.springframework.data.domain.Page;


/**
 * @Author: 98050
 * @Time: 2018-11-26 15:40
 * @Feature:
 */
public interface CommentService {

    /**
     * 1 根据评论id查询
     *
     * @param id
     * @return
     */
    Review findOne(String id);

    /**
     * 2 新增评论
     *
     * @param review
     * @param orderId
     * @return
     */
    boolean add(Long orderId, Review review);

    /**
     * 3 修改评论
     *
     * @param review
     */
    void update(Review review);

    /**
     * 4 删除指定评论
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 5 查询某一商品下的所有顶级评论
     *
     * @param commentRequestParam
     * @return
     */
    Page<Review> findReviewBySpuId(CommentRequestParam commentRequestParam);

    /**
     * 6 评论点赞
     *
     * @param id
     */
    boolean updateThumbup(String id);

    /**
     * 7 浏览量增1
     *
     * @param id
     */
    boolean updateVisits(String id);
}
