package com.diane.manage.service;

import com.diane.manage.vo.MsgVo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "ws")
public class WebsocketService {

    @RabbitHandler
    public void pushMessage(MsgVo msgVo) {
        System.out.println(msgVo.toString());
    }
    }
