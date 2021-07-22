package com.odianyun.demo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.odianyun.demo.model.User;
import com.odianyun.demo.startup.Application;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;



    @Test
    public void testStringSet(){
        ValueOperations operations = redisTemplate.opsForValue();

        String key="userName:2";

        operations.set(key,"xuzhi");
//        同一个key第二次赋值会覆盖第一次的值
        operations.set(key,"zhangwuji");

        System.out.println("value = " +operations.get(key));
    }

    @Test
    public void testStringExpire() throws InterruptedException {
        ValueOperations operations = redisTemplate.opsForValue();
        String key = "userCaptchas:1";
//        设置过期时间，3秒
        operations.set(key,"令狐冲", 3,TimeUnit.SECONDS);
        System.out.println("value = " + operations.get(key));
//        程序睡眠四秒，四秒后获取key的值
        Thread.sleep(4000);
        System.out.println("value = " + operations.get(key));
    }

    @Test
    public void testStringSetIfAbsent(){
        ValueOperations<String, Serializable> operations = redisTemplate.opsForValue();
        String key="userName:2";
        String key2="userName:3";
        String key4="userName:4";
//        setIfAbsent:设置key和value，如果存在则不改变value的值，如果不存在,则创建一个key和set
        operations.setIfAbsent(key,"wuyifan");
        operations.setIfAbsent(key2,"令狐冲");
        operations.setIfAbsent(key4,"张无忌");
        operations.setIfAbsent(key4,"岳不群");

        System.out.println("key value = " + operations.get(key));
        System.out.println("key2 value = " + operations.get(key2));
        System.out.println("key4 value = " + operations.get(key4));
    }

    @Test
    public void testStringSetIfPresent(){
        ValueOperations<String, Serializable> operations = redisTemplate.opsForValue();
        String key="userName:2";
        String key2="userName:3";
        String key5="userName:5";
//      setIfPresent:如果key存在则使用新值覆盖，如果key不存在，则不赋值。
        operations.setIfPresent(key,"迪迦");
        operations.setIfPresent(key2,"任盈盈");
        operations.setIfPresent(key5,"任我行");

        System.out.println("key value = " + operations.get(key));
        System.out.println("key2 value = " + operations.get(key2));
        System.out.println("key5 value = " + operations.get(key5));
    }

    @Test
    public void testStringMultiSet(){
        ValueOperations operations = redisTemplate.opsForValue();
        /*HashMap<Object, Object> map = new HashMap<>();
        map.put("userName:001","陈江河");
        map.put("username:002","骆玉珠");
        map.put("userName:003","邱英杰");*/

//        批量设置key和value
        Map<String, String> map = ImmutableMap.of(
                "userName:001","陈江河",
                "userName:002","骆玉珠",
                "userName:003","邱英杰"
        );

        operations.multiSet(map);

        System.out.println("userName:001 = " + operations.get("userName:001"));

//        批量获取key的值
        List multiValues = operations.multiGet(Lists.newArrayList("userName:001", "userName:002", "userName:003"));

//        输出所有的value
//       /*1*/ multiValues.forEach(System.out::println);

        /*2*/multiValues.forEach(item -> System.out.println(item));

//        返回除了邱英杰的value
        List<String> filterList = (List<String>) multiValues.stream().filter(item -> !item.equals("邱英杰")).collect(Collectors.toList());
        System.out.println("filterList = " + JSONObject.toJSONString(filterList));
    }

    @Test
    public void testStringIncrement(){
        ValueOperations operations = redisTemplate.opsForValue();
        String key = "url:query:num";
        Long maxNum = 10L;
        Long num = 0L;
        operations.set(key,0);

        while (maxNum.compareTo(num)>0){
//            加值
            num = operations.increment(key);
            System.out.println("num = " + num);
        }
    }

    @Test
    public void testStringIncrementWithDelta(){
        ValueOperations<String, Serializable> operations = redisTemplate.opsForValue();
        String key = "url:query:num";
        Long maxNum = 10L;
        Long num = 0L;
        Long delta = 2L;
        operations.set(key,0);
        while (maxNum.compareTo(num) > 0){
//            减值,每次减2
            num = operations.increment(key, delta);
            System.out.println("num = " + num);
        }
    }

    @Test
    public void testStringGetAndSet(){
        ValueOperations<String, Serializable> operations = redisTemplate.opsForValue();
        String key = "url:query:num";
        String key3 = "url:query:num3";

//        设置新值返回旧值，如果不存在旧值就是null
        System.out.println("getAndSet key value= " + operations.get(key));
        System.out.println("getAndSet key value= " + operations.getAndSet(key,"吴京"));
        System.out.println("getAndSet key value= " + operations.get(key));

        System.out.println("getAndSet key value= " + operations.get(key3));
        System.out.println("getAndSet key value= " + operations.getAndSet(key3,"张译"));
        System.out.println("getAndSet key value= " + operations.get(key3));
    }

    @Test
    public void testObject(){
        ValueOperations<String, Serializable> operations = redisTemplate.opsForValue();
        String key="userInfo:123";
        User user = new User();
        user.setId(12L);
        user.setMobile("456456487");
        user.setPassword("123456");

        operations.set(key,user);

        User cacheuser = (User) operations.get(key);

        System.out.println(JSONObject.toJSONString(cacheuser));
    }

    /**
     * 操作list
     */
    @Test
    public void testListTrim(){
        ListOperations<String, Serializable> operations = redisTemplate.opsForList();
        String key = "user:fans:1";


        /*operations.leftPush(key,"李白");
        operations.leftPush(key,"杜甫");
        operations.leftPush(key,"王安石");
        operations.leftPush(key,"张译");
*/
//        添加到list的右边，即尾部
        operations.rightPush(key,"吴京");

//        将value的值插入到pivot前面
        operations.leftPush(key,"王安石","甄子丹");

        List<Serializable> values = operations.range(key, 0, -1);
        System.out.println("values = " + JSONObject.toJSONString(values));
    }

    @Test
    public void testListPushAll(){
        ListOperations<String, Serializable> operations = redisTemplate.opsForList();
        String key = "user:fan:2";
//        删除key中的所有值
        redisTemplate.delete(key);

        operations.leftPushAll(key,"令狐冲","东方不败");
        operations.leftPushAll(key,Lists.newArrayList("岳不群","岳灵珊"));

        operations.rightPushAll(key,"任我行","任盈盈");
        operations.rightPushAll(key,Lists.newArrayList("周冲","花荣"));

        List<Serializable> values = operations.range(key, 0, -1);

        System.out.println("values = " + values);
//        values = [岳灵珊, 岳不群, 东方不败, 令狐冲, 任我行, 任盈盈, 周冲, 花荣]
    }

    @Test
    public void testListPushIfPresent(){
        ListOperations<String, Serializable> operations = redisTemplate.opsForList();

        String key = "user:fan:3";
        String key2 = "user:fan:2";

//        leftPushIfPresent:当key有值的时候，左边添加，否则不做任何操作
        operations.leftPushIfPresent(key,"邓紫棋");
        operations.leftPushIfPresent(key,"张杰");
        operations.leftPushIfPresent(key,"周杰伦");

        operations.leftPushIfPresent(key2,"金轮法王");
        operations.leftPushIfPresent(key2,"李逍遥");
        operations.leftPushIfPresent(key2,"天山童姥");


        List<Serializable> range = operations.range(key2, 0, -1);
        System.out.println("range = " + JSONObject.toJSONString(range));
        List<Serializable> range1 = operations.range(key, 0, -1);
        System.out.println("range1 = " + JSONObject.toJSONString(range1));
    }

    @Test
    public void testListPop(){
        ListOperations<String, Serializable> operations = redisTemplate.opsForList();
//        range = [天山童姥, 李逍遥, 金轮法王, 天山童姥, 李逍遥, 金轮法王, 岳灵珊, 岳不群, 东方不败, 令狐冲, 任我行, 任盈盈, 周冲, 花荣]
        String key = "user:fan:2";

        System.out.println("old values = " + JSONObject.toJSONString(operations.range(key,0,-1)));

//        左删除,删除最左的一个值
        String leftvalue = (String) operations.leftPop(key);
//        右删除，删除最右的一个值
        String rightvalue = (String) operations.rightPop(key);

        System.out.println("leftvalue = " + leftvalue);
        System.out.println("rightvalue = " + rightvalue);

        System.out.println("current value = " + JSONObject.toJSONString(operations.range(key,0,-1)));
    }

    @Test
    public void testListRemove() {
        ListOperations<String, Serializable> operations = redisTemplate.opsForList();
        String key = "user:fan:2";
        System.out.println("old value = " + JSONObject.toJSONString(operations.range(key, 0, -1)));

        operations.leftPush(key, "小龙女");
        System.out.println("old value = " + JSONObject.toJSONString(operations.range(key, 0, -1)));

//        删除指定个数的value，并返回删除个数
        Long num = operations.remove(key, 5, "小龙女");
        System.out.println("num = " + num);
        System.out.println("current value = " + JSONObject.toJSONString(operations.range(key, 0, -1)));
    }

    @Test
    public void testListRightPopAndLeftPush(){
        ListOperations<String, Serializable> operations = redisTemplate.opsForList();
        String key = "user:fan:2";
        String key1 = "user:fan:5";

        System.out.println("key value = " + JSONObject.toJSONString(operations.range(key,0,-1)));
        System.out.println("key1 value = " + JSONObject.toJSONString(operations.range(key1,0,-1)));
//        把key中右边的值移除到key1中的左边
        operations.rightPopAndLeftPush(key,key1);

        System.out.println("key value = " + JSONObject.toJSONString(operations.range(key,0,-1)));
        System.out.println("key1 value = " + JSONObject.toJSONString(operations.range(key1,0,-1)));
    }

    @Test
    public void testListRightPopAndLeftPushWithTimeout(){
        ListOperations<String, Serializable> operations = redisTemplate.opsForList();
        String key = "user:fan:6";
        String key2 = "user:fan:5";

        System.out.println("key value = " + JSONObject.toJSONString(operations.range(key,0,-1)));
        System.out.println("key2 value = " + JSONObject.toJSONString(operations.range(key2,0,-1)));

        Long size = operations.size(key2);
        System.out.println("size = " + size);

        operations.leftPush(key,"玛丽莲梦露");

//        获取当前毫秒数
        long start = System.currentTimeMillis();
//        把key中右边的值移除到key1中的左边,并在2秒后
        operations.rightPopAndLeftPush(key,key2,2,TimeUnit.SECONDS);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end-start)/1000 + "s");

        System.out.println("key value = " + JSONObject.toJSONString(operations.range(key,0,-1)));
        System.out.println("key2 value = " + JSONObject.toJSONString(operations.range(key2,0,-1)));
    }



}
