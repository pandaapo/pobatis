import com.panda.pobatis.BlogMapper;
import com.panda.pobatis.MyConfiguration;
import com.panda.pobatis.MyExecutor;
import com.panda.pobatis.MySqlSession;

public class PoBatisTest {
    public static void main(String[] args) {
        MySqlSession sqlSession = new MySqlSession(new MyConfiguration(), new MyExecutor());
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.selectBlogById(1);
    }
}
