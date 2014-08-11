package com.lerith.akka.chapter1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class HelloWorld {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef greeter = system.actorOf(Props.create(GreetingActor.class), "greeter");
        greeter.tell(new Greeting("Charlie Parker"), ActorRef.noSender());
    }
}
