package com.dream.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisHashTest extends BaseTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void set() {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        String key = "user:1";
        //  stringRedisTemplate.expire(key, 100, TimeUnit.SECONDS);
        ops.put(key, "name", "woody");
        ops.put(key, "age", 18);
        ops.put(key, "email", "343618924@qq.com");
        ops.put(key, "address", "成都市");
        ops.put(key, "sex", "男");
    }

    @Test
    public void multiSet() {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        String key = "user:2";
        Map<String, String> map = new HashMap<>();
        map.put("name", "吴波");
        map.put("age", "29");
        map.put("email", "343618924@qq.com");
        map.put("sex", "男");
        ops.putAll(key, map);
    }

    @Test
    public void get() {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        String key = "user:1";
        String hashKey = "name";
        String name = ops.get(key, hashKey).toString();
        System.out.println(name);
    }

    @Test
    public void multiGet() {
        HashOperations<String, String, Object> ops = redisTemplate.opsForHash();
        String key = "user:2";
        List<String> hashKey = new ArrayList<>();
        hashKey.add("age");
        hashKey.add("name");
        hashKey.add("email");
        hashKey.add("address");
        hashKey.add("aaa");
        List<Object> values = ops.multiGet(key, hashKey);
        values.stream().forEach(value -> System.out.println(value));
    }

    @Test
    public void keys() {
        HashOperations<String, String, Object> ops = redisTemplate.opsForHash();
        String key = "user:2";
        Set<String> keys = ops.keys(key);
        keys.stream().forEach(k -> System.out.println(k));
    }

    @Test
    public void hasKey() {
        HashOperations<String, String, Object> ops = redisTemplate.opsForHash();
        String key = "user:2";
        Boolean name = ops.hasKey(key, "name");
        System.out.println("name: " + name);
        Boolean bar = ops.hasKey(key, "bar");
        System.out.println("bar: " + bar);
    }
}

