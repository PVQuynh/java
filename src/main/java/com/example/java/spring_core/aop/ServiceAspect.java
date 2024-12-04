package com.example.java.spring_core.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceAspect {

    public void methodAspect(int arg) {
        log.info("methodAspect được gọi");
        if (arg < 0 ) throw new RuntimeException();
    }
}
