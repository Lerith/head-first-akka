package com.lerith.akka.chapter1;

import java.io.Serializable;

/**
 * Created by user on 2014/8/11.
 */


public class Greeting implements Serializable {
    private static final long serialVersionUID = 3644814306392181619L;
    public final String who;

    public Greeting(String who) {
        this.who = who;
    }
}






