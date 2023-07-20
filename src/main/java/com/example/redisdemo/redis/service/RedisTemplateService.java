package com.example.redisdemo.redis.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisTemplateService {

    private final StringRedisTemplate redisTemplate;

    final int MAX_CNT = 100000;
    public void setStrings() {
        final String key = "string:key";

        final ValueOperations<String, String> stringValueOperations = redisTemplate.opsForValue();

        stringValueOperations.set(key, "1"); // redis set 명령어
        final String result_1 = stringValueOperations.get(key); // redis get 명령어
        System.out.println("result_1 = " + result_1);

        stringValueOperations.increment(key); // redis incr 명령어
        final String result_2 = stringValueOperations.get(key);
        System.out.println("result_2 = " + result_2);

        // redis-cli> get string_key
    }

    
    // todo: 10만개의 userid 를 String에 add하여 테스트
    public void setLongStrings() {
        final String key = "long:key";
        String userIds = "";


        final ValueOperations<String, String> longStrValueOperations = redisTemplate.opsForValue();

        //      10만개 생성 test
//        for(int i = 0; i < MAX_CNT; i++) {
//            userIds = userIds + "*" + String.valueOf(i);
//        }
//        longStrValueOperations.set(key, userIds); // redis set 명령어

        // todo : 특정 userID를 삭제하여 다시 저장 -> user가 관심 리스트 삭제시
        final String result_2 = longStrValueOperations.get(key);
        System.out.println("userIDs " + result_2);
        // redis-cli> get string_key
    }

    public void cmdSets() {
        String key = "sets:key";
        SetOperations<String, String> stringSetOperations = redisTemplate.opsForSet();

////         10만개 생성 test
//        for(int i = 0; i < MAX_CNT; i++) {
//            stringSetOperations.add(key, String.valueOf(i));
//        }

        Set<String> set = stringSetOperations.members(key);
        System.out.println("all members = " + Arrays.toString(set.toArray())); // 전체 조회

        Long size = stringSetOperations.size(key);
        System.out.println("size = " + size); // size

        // Cursor 조회는 사용 안함
//        try (Cursor<String> cursor = stringSetOperations.scan(key, ScanOptions.scanOptions().match("*").count(3).build())) {
//            while (cursor.hasNext()) {
//                System.out.println("cursor = " + cursor.next());
//            }
//        }
        // redis-cli>  smembers set_key
    }

}
