package com.dream.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisStringTest extends BaseTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void set() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("name", "张三");
    }


    @Test
    public void multiSet() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        Map<String, String> map = new HashMap<>();
        map.put("name", "吴波");
        map.put("age", "29");
        map.put("email", "343618924@qq.com");
        map.put("sex", "男");
        ops.multiSet(map);
    }


    @Test
    public void setExpire() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("name", "张三", 100, TimeUnit.SECONDS);
    }

    @Test
    public void get() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String name = ops.get("name");
        System.out.println(name);
    }

    @Test
    public void multiGet() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        List<String> keys = new ArrayList<>();
        keys.add("age");
        keys.add("name");
        keys.add("email");
        keys.add("address");
        List<String> values = ops.multiGet(keys);
        System.out.println(Arrays.toString(values.toArray()));
    }

    @Test
    public void increment() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String key = "age";
        String oldAge = ops.get(key);
        System.out.println("oldAge : " + oldAge);
        Long newAge = ops.increment(key, 100);
        System.out.println("newAge : " + newAge);
    }

    @Test
    public void decrement() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String key = "age";
        String oldAge = ops.get(key);
        System.out.println("oldAge : " + oldAge);
        Long newAge = ops.decrement(key, 100);
        System.out.println("newAge : " + newAge);
    }

    @Test
    public void append() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String key = "name";
        //每个中文占3个字节
        Integer append = ops.append(key, "吴波");
        System.out.println("append : " + append);
    }

    @Test
    public void getAndSet() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String key = "name";
        //返回旧值,旧值不存在返回空
        String name = ops.getAndSet(key, "张三");
        System.out.println(name);
    }

    @Test
    public void delete() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String key = "name";
        stringRedisTemplate.delete(key);
    }

    @Test
    public void multiDelete() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        List<String> keys = new ArrayList<>();
        keys.add("age");
        keys.add("name");
        keys.add("email");
        keys.add("address");
        stringRedisTemplate.delete(keys);
    }

}

