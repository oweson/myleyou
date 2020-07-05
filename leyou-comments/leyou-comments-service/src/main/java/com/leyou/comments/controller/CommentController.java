package com.leyou.comments.controller;

import com.leyou.auth.entity.UserInfo;
import com.leyou.comments.bo.CommentRequestParam;
import com.leyou.comments.interceptor.LoginInterceptor;
import com.leyou.comments.pojo.Review;
import com.leyou.comments.service.CommentService;
import com.leyou.common.pojo.PageResult;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 98050
 * @Time: 2018-11-26 21:30
 * @Feature:
 */
@RequestMapping
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;

    private final String THUMBUP_PREFIX = "thumbup";

    /**
     * 1 分页查询某一商品下的所有顶级评论
     * 评论的下面还有二级评论
     *
     * @param requestParam 请求bo对象
     * @return
     */
    @GetMapping("list")
    public ResponseEntity findReviewBySpuId(@RequestBody CommentRequestParam requestParam) {
        Page<Review> result = commentService.findReviewBySpuId(requestParam);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotalElements());
        pageResult.setItems(result.getContent());
        // 总的页数，简介的分页信息封装；
        pageResult.setTotalPage((long) result.getTotalPages());
        return ResponseEntity.ok(pageResult);
    }

    /**
     * 2 评论点赞
     *
     * @param id
     * @return
     */
    @PutMapping("thumb/{id}")
    public ResponseEntity<Boolean> updateThumbup(@PathVariable String id) {

        //1.首先判断当前用户是否点过赞
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        String userId = userInfo.getId() + "";
        if (redisTemplate.opsForValue().get(THUMBUP_PREFIX + userId + "_" + id) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        boolean result = this.commentService.updateThumbup(id);
        if (!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // 存储点过的人的信息
        redisTemplate.opsForValue().set(THUMBUP_PREFIX + userId + "_" + id, "1");
        return ResponseEntity.ok(result);
    }

    @PutMapping("thumb/{id}")

    public ResponseEntity<Boolean> updateThumbup02(@PathVariable String id) {
        // 1 首先判断是否已经点过了；
        UserInfo loginUser = LoginInterceptor.getLoginUser();
        String userId = loginUser.getId() + "";
        // 点过了已经
        if (redisTemplate.opsForValue().get(THUMBUP_PREFIX + userId + "_" + id) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        boolean updateThumbup = this.commentService.updateThumbup(id);
        if (!updateThumbup) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        redisTemplate.opsForValue().set(THUMBUP_PREFIX + userId + "_" + id, 1);
        return ResponseEntity.ok(updateThumbup);
    }

    /**
     * 3 增加评论
     * todo 评价订单！ok
     * @param review
     * @return
     */
    @PostMapping("comment/{orderId}")
    public ResponseEntity<Void> addReview(@PathVariable("orderId") Long orderId, @RequestBody Review review) {
        boolean result = this.commentService.add(orderId, review);
        if (!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 4 根据评论id查询评论
     * ok;
     * @param id
     * @return
     */
    @GetMapping("/commentId/{id}")
    public ResponseEntity<Review> findReviewById(@PathVariable("id") String id) {
        Review review = this.commentService.findOne(id);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(review);
    }

    /**
     * 5 修改评论
     * ok!
     * @param review
     * @return
     */
    @PutMapping("/comment")
    public ResponseEntity<Void> updateReview(@RequestBody Review review) {
        this.commentService.update(review);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 6 根据评论id删除评论
     * ok
     * @param id
     * @return
     */
    @DeleteMapping("/commentId/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") String id) {
        this.commentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 7 根据评论id访问量加1
     *  ok 评论的数量加一
     * @param id
     * @return
     */
    @PutMapping("visit/{id}")
    public ResponseEntity<Void> updateReviewVisit(@PathVariable("id") String id) {
        boolean result = this.commentService.updateVisits(id);
        if (!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
