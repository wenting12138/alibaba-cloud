package com.wenting.springcloud.service.impl;

import com.wenting.springcloud.service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import javax.annotation.Resource;
import java.util.UUID;


@EnableBinding(Source.class)  // 定义消息的推送管道
@Slf4j
public class MessageProviderImpl implements MessageProvider {

    @Resource
    private MessageChannel output;   // 消息发送管道
    public String end() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("发送消息serial: =========>" + serial);
        return "消息发送成功(*^_^*),流水号: ===>" + serial;
    }
}
