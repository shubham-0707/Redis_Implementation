package com.example.Redis_Implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {


    // This controller file is the file where all the APIs are defined along with their end-points...
    @Autowired
    RedisTemplate<String , User> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/set_value")
    public void addValue(@RequestParam("key") String key , @RequestBody() User user){
        redisTemplate.opsForValue().set(key , user);
    }

    @GetMapping("/get_value")
    public User getValue(@RequestParam("key") String key){
        return (User)redisTemplate.opsForValue().get(key);
    }

    @PostMapping("/lpush")
    public void leftPush(@RequestParam("key") String key , @RequestBody() User user){
        redisTemplate.opsForList().leftPush(key , user);
    }

    @PostMapping("/rpush")
    public void rightPush(@RequestParam("key") String key , @RequestBody() User user){
        redisTemplate.opsForList().rightPush(key , user);
    }

    @GetMapping("/lpop")
    public User getLPop(@RequestParam("key") String key){
        return (User) redisTemplate.opsForList().leftPop(key);
    }

    @PostMapping("/hmset")
    public void addHashValue(@RequestParam("key") String key , @RequestBody() User user){
        Map map = objectMapper.convertValue(user , Map.class);
        redisTemplate.opsForHash().putAll(key , map);
    }

    @GetMapping("/hget")
    public String getHashValue(@RequestParam("key") String key , @RequestParam("hashkey") String hashkey){
        return (String) redisTemplate.opsForHash().get(key , hashkey);
    }

    @GetMapping("/hgetall")
    public User getHashValueAll(@RequestParam("key") String key){
        Map map = redisTemplate.opsForHash().entries(key);
        User user = objectMapper.convertValue(map , User.class);
        return user;
    }

}
