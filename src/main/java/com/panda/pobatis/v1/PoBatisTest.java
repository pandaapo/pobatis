package com.panda.pobatis.v1;

import com.panda.pobatis.v1.mapper.BlogMapper;

public class PoBatisTest {
    public static void main(String[] args) {
        MySqlSession sqlSession = new MySqlSession(new MyConfiguration(), new MyExecutor());
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.selectBlogById(1);
    }
}
