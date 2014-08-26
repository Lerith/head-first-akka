package com.lerith.akka.chapter2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by user on 2014/8/26.
 */
public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Actors");
        ActorRef actorRef = system.actorOf(Props.create(MyUntypedActor.class), "myUntypedActor");
        actorRef.tell("Action", ActorRef.noSender());

    }
}
