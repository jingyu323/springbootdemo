package com.htkj.config;

import com.htkj.enums.TypeEnum;
import com.htkj.parser.DeviceStateParser;
import com.htkj.parser.TrainInfoDataParser;
import com.htkj.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * The type Redis key expiration listener.
 */
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Resource
    private TrainInfoDataParser trainInfoDataParser;

    @Resource
    private DeviceStateParser deviceStateParser;

    /**
     * Instantiates a new Redis key expiration listener.
     *
     * @param listenerContainer the listener container
     */
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();

//        if (expiredKey.contains(TypeEnum.MESSAGE.getKey())){
//            log.info("过期key:"+expiredKey);
//            trainInfoDataParser.parseTrainInfoMessage(expiredKey);
//        }

        if (expiredKey.contains(TypeEnum.STATE.getKey())){
            log.info(expiredKey);
            deviceStateParser.parseDeviceStateMessage(expiredKey);
        }
    }
}

