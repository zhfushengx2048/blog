package xyz.fusheng.blog;

        import org.mybatis.spring.annotation.MapperScan;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author code_fusheng
 * @Date: 2020/3/10 22:04  --  2020/3/11 15:42
 */
@SpringBootApplication
@MapperScan("xyz.fusheng.blog.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
