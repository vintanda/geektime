package week11.cache.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLock {

    @Resource
    private RedissonClient redissonClient;

    public boolean tryLock(String key) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(1, 2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }

}