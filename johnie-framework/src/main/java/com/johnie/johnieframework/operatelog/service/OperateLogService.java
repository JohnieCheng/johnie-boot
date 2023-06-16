package com.johnie.johnieframework.operatelog.service;

import com.johnie.johnieframework.common.cache.RedisCache;
import com.johnie.johnieframework.common.cache.RedisKeys;
import com.johnie.johnieframework.operatelog.dto.OperateLogDTO;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 操作日志服务
 */
@Service
@AllArgsConstructor
public class OperateLogService {
    private final RedisCache redisCache;

    @Async
    public void saveLog(OperateLogDTO log) {
        String key = RedisKeys.getLogKey();

        // 保存到Redis队列
        redisCache.leftPush(key, log, RedisCache.NOT_EXPIRE);
    }
}
