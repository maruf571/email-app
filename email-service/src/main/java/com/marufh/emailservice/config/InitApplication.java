package com.marufh.emailservice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@Profile("dev")
@RequiredArgsConstructor
public class InitApplication implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws IOException {
        log.info("...init application...");
    }
}
