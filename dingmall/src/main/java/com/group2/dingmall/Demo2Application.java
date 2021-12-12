package com.group2.dingmall;

import com.group2.dingmall.utils.IPUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
//指定mapper接口类的位置
@EnableSwagger2
public class Demo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
        System.out.println("swagger： "+ String.format("http://%s:8080/doc.html", IPUtil.getCurrentIP()));
        System.out.println();
    }

}
