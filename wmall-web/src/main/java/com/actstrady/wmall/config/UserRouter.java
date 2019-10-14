package com.actstrady.wmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import po.UserPO;
import reactor.core.publisher.Mono;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/10/12 10:02
 * @fileName : UserRouter.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@Configuration
public class UserRouter {
    /**
     * 功能端点路由函数
     *
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> userRoute() {
        return RouterFunctions.route(RequestPredicates.GET("/user"),
                // java8的函数式编程
                request -> {
                    UserPO userPO = new UserPO("1", "1", "1", "1");
                    Mono<UserPO> userPOMono = Mono.just(userPO);
                    return ServerResponse.ok().body(userPOMono, UserPO.class);
                });
    }
}
