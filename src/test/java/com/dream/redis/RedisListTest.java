package com.dream.redis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisListTest extends BaseTest {

    private static final String key = "list";

    protected ListOperations ops;

    @Before
    public void before() {
        ops = redisTemplate.opsForList();
    }

    /**
     * 添加命令：
     * rpush james c b a //从右向左插入cba, 返回值3
     * lrange james 0 -1 //从左到右获取列表所有元素 返回 c b a
     * lpush key c b a //从左向右插入cba
     * linsert james before b teacher //在b之前插入teacher, after为之后，使
     * 用lrange james 0 -1 查看：c teacher b a
     */
    @Test
    public void leftPush() {
        ops.leftPush(key, "张三");
        ops.leftPush(key, "李四");
        ops.leftPush(key, "王五");
        ops.leftPush(key, "will");
        ops.leftPush(key, "Lily");
    }

    @Test
    public void range() {
        List range = ops.range(key, 0, -1);
        range.stream().forEach(value -> System.out.println(value));
    }

    @Test
    public void set() {
        Long index = 0L;
        String value = "Lily-update";
        ops.set(key, index, value);
    }

    @Test
    public void leftPop() {
        ops.leftPop(key);
    }

}

