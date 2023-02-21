package com.noritakakagei.sample;

import org.springframework.stereotype.Component;

/**
 * Implemented Greet class
 * greeting in the morning
 */
// @Component
public class MorningGreet implements Greet {
    @Override
    public void greeting() {
        System.out.println("Good Morning!!");
    }
}