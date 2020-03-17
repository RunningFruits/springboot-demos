package cn.huanzi.qch.springbootsecurity;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

@SpringBootApplication
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class SpringbootSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityApplication.class, args);
    }

}
