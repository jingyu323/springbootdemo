package com.rain.test.controller;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.rain.test.common.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/testRedis")
@Slf4j
public class TestRedisController {

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("name-%d").setDaemon(true).build();


    private static final ScheduledExecutorService daemonPool = Executors.newScheduledThreadPool(5, THREAD_FACTORY);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/testSetNX")
    public JsonResult testSetNX(@RequestParam Long goodsId) {
        String key = "lock_" + goodsId;
        String value = UUID.randomUUID().toString();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        ScheduledFuture<?> scheduledFuture = null;
        try {
            // 加锁
            Boolean ifAbsent = valueOperations.setIfAbsent(key, value, 30, TimeUnit.SECONDS);
            log.info("加锁{}返回值：{}", key, ifAbsent);
            if ((null == ifAbsent) || (!ifAbsent)) {
                log.info("加锁失败，请稍后重试！");
                return new JsonResult(true, "加锁失败，请稍后重试", null);
            }
            // 模拟看门狗逻辑
            AtomicInteger count = new AtomicInteger(1);
            scheduledFuture = daemonPool.scheduleWithFixedDelay(() -> {
                log.info("看门狗第：{}次执行开始", count.get());
                Object cache = redisTemplate.opsForValue().get(key);
                if (Objects.nonNull(cache) && (value.equals(cache.toString()))) {
                    // 重新设置有效时间为30秒
                    redisTemplate.expire(key, 30, TimeUnit.SECONDS);
                    log.info("看门狗第：{}次执行结束，有效时间为：{}", count.get(), redisTemplate.getExpire(key));
                } else {
                    log.info("看门狗执行第：{}次异常：key：{} 期望值：{} 实际值：{}", count.get(), key, value, cache);
                }
                count.incrementAndGet();
            }, 10, 10, TimeUnit.SECONDS);
            // 执行业务逻辑
            TimeUnit.SECONDS.sleep(5);
            log.info("业务逻辑执行结束");
        } catch (Exception e) {
            log.error("testSetNX exception:", e);
            return new JsonResult(false, "加锁失败，请稍后重试", null);
        } finally {
            // 释放锁，判断是否是当前线程加的锁
            String delVal = valueOperations.get(key).toString();
            if (value.equals(delVal)) {
                Boolean delete = redisTemplate.delete(key);
                log.info("释放{}锁结果：{}", key, delete);
                // 关闭看门狗线程
                if (Objects.nonNull(scheduledFuture)) {
                    boolean cancel = scheduledFuture.cancel(true);
                    log.info("关闭看门狗结果：{}", cancel);
                }
            } else {
                log.info("不予释放，key：{} value：{} delVal：{}", key, value, delVal);
            }
        }
        return new JsonResult(true, "加锁失败，请稍后重试", null);
    }

}
