package com.noritakakagei.sample.di;

import org.springframework.stereotype.Component;

/**
 * Implemented Greet class
 * greeting in the evening
 */
@Component
public class EveningGreet implements Greet {
    @Override
    public void greeting() {
        System.out.println("Good Evening!!");
    }
}