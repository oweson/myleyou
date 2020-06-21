package com.leyou.listener;

import com.leyou.service.GoodsHtmlService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: 98050
 * @Time: 2018-10-21 14:45
 * @Feature: mq监听器，消费者
 */
@Component
public class GoodsListener {

    @Autowired
    private GoodsHtmlService goodsHtmlService;

    /**
     * 1 处理insert和update的消息
     *
     * @param id
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "leyou.create.web.queue", durable = "true"),
            //队列持久化
            exchange = @Exchange(
                    value = "leyou.item.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"item.insert", "item.update"}
    ))
    public void listenCreate(Long id) throws Exception {
        if (id == null) {
            return;
        }
        //创建或更新索引，ES需要
        this.goodsHtmlService.createHtml(id);
    }

    /**
     * 2 处理删除的消息
     *     todo //支持自动声明绑定，声明之后自动监听队列的队列，此时@RabbitListener注解的queue和bindings不能同时指定，否则报错
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "leyou.delete.web.queue", durable = "true"),
            //队列持久化
            exchange = @Exchange(
                    value = "leyou.item.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"item.delete"}
    ))
    public void listenDelete(Long id) {
        if (id == null) {
            return;
        }
        //删除索引
        this.goodsHtmlService.deleteHtml(id);
    }


}
