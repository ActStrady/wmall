package com.actstrady.wmall.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import po.UserPO;
import reactor.core.publisher.Mono;

/**
 * 路由配置类
 *
 * @author : ActStrady@tom.com
 * @date : 2019/10/11 20:32
 * @fileName : UserHandler.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@Configuration
public class UserHandler {
    @Bean
    public RouterFunction<ServerResponse> userRoute() {
        return RouterFunctions.route(RequestPredicates.GET("/user"),
                request -> {
                    UserPO userPO = new UserPO("1", "1", "1", "1");
                    Mono<UserPO> userPOMono = Mono.just(userPO);
                    return ServerResponse.ok().body(userPOMono, UserPO.class);
        });
    }
}
