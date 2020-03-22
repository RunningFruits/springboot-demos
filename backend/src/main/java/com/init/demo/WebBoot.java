package com.init.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WebBoot {

    public static void main(String[] args) {
        SpringApplication.run(WebBoot.class, args);
    }

//    @Bean
//    public TomcatServletWebServerFactory tomcatEmbedded() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
//            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
//                //-1 means unlimited
//                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
//            }
//        });
//        return tomcat;
//    }

}
