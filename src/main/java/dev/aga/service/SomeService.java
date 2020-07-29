package dev.aga.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class SomeService {

    public void throwIllegalArgumentException() {
        sleep();
        throw new IllegalArgumentException("Illegal Argument");
    }

    public void throwIllegalStateException() {
        sleep();
        throw new IllegalStateException("Illegal State");
    }

    private static void sleep() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(250));
        } catch (InterruptedException e) {
            //
        }
    }
}
