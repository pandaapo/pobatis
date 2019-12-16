package com.panda.pobatis.v1;

import com.panda.pobatis.v1.BlogMapper;
import com.panda.pobatis.v1.MyConfiguration;
import com.panda.pobatis.v1.MyExecutor;
import com.panda.pobatis.v1.MySqlSession;

public class PoBatisTest {
    public static void main(String[] args) {
        MySqlSession sqlSession = new MySqlSession(new MyConfiguration(), new MyExecutor());
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.selectBlogById(1);
    }
}
