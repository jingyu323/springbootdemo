package com.rain.test.threadpooldemo.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class EhCacheUtils {
    public static final Logger logger = LoggerFactory.getLogger(EhCacheUtils.class);

    /** CacheManager */
    private static  CacheManager cacheManager;
    @Autowired
    public   void setCacheManager(CacheManager cacheManager) {
        EhCacheUtils.cacheManager = cacheManager;
    }

    /**
     * 获取Cache
     *
     * @author hkt
     * @date 2021/3/1 12:49
     */
    public static Cache getCache() {
        return cacheManager.getCache("fileCreateQueue");
    }

    /**
     * 添加缓存数据
     *
     * @param key   键
     * @param value 值
     * @author hkt
     * @date 2021/3/1 12:50
     */
    public static void put(String key, Object value) {
        try {
            Cache cache = getCache();
            cache.put(key, value);
        } catch (Exception e) {
            logger.error("添加缓存失败：{}", e.getMessage());
        }
    }

    /**
     * 获取缓存数据
     *
     * @param key 键
     * @return 缓存数据
     * @author hkt
     * @date 2021/3/1 12:53
     */
    public static <T> T get(String key) {
        try {
            Cache cache = getCache();
            return (T) cache.get(key).get();
        } catch (Exception e) {
            logger.error("获取缓存数据失败：", e);
            return null;
        }
    }

    /**
     * 删除缓存数据
     *
     * @param key 键
     * @author hkt
     * @date 2021/3/1 12:53
     */
    public static void delete(String key) {
        try {
            Cache cache = getCache();
            cache.evict(key);
        } catch (Exception e) {
            logger.error("删除缓存数据失败：", e);
        }
    }

    /**
     * @author hkt
     * @date 2021/3/1 12:02
     */
    private EhCacheUtils() {
    }
}
