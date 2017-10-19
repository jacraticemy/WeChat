package cn.nexuslink.service.impl;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 罗浩 on 2017/4/25.
 */
@Service("cacheService")
public class CacheService {

    @Resource
    private EhCacheCacheManager cacheManager;

    public Object getCacheObj(String cacheName,String key){
        return cacheManager.getCache(cacheName).get(key).get();
    }

    public void addToCache(String cacheName,String key,Object value){
        cacheManager.getCache(cacheName).put(key,value);
    }

}
