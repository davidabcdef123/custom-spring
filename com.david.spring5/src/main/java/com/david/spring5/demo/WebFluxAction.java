package com.david.spring5.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sc on 2018/12/1.
 */
@RestController
public class WebFluxAction {

    //必须要依赖Servlet 3.x
    //如果你的返回结果是一个单体的实例，那么你就用Mono
    @GetMapping("mono")
    public Mono<Member> mono(@RequestParam String name) {
        Member member = new Member();
        member.setName(name);
        return Mono.just(member);
    }

    @GetMapping("flux")
    public Flux<Member> flux(){
        List<Member> result = new ArrayList<>();
        Member tom = new Member();
        tom.setName("Tom");
        result.add(tom);

        Member mic = new Member();
        mic.setName("Mic");
        result.add(mic);
        return Flux.fromIterable(result);
    }



}
