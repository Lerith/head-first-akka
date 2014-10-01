package com.lerith.akka.chapter2.message;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2014/10/1.
 */
public class MessageApp {
    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("MessageApp");
        ActorRef messageActor = system.actorOf(Props.create(MessageActor.class), "messageActor");
        ActorRef askActor = system.actorOf(Props.create(AskActor.class), "askActor");
        ActorRef receiveTimeoutUntypedActor = system.actorOf(Props.create(ReceiveTimeoutUntypedActor.class), "receiveTimeoutUntypedActor");


        //receive timeout
        receiveTimeoutUntypedActor.tell("Timeout",ActorRef.noSender());

        Thread.sleep(10000);
        //tell
        List list = Arrays.asList("ab", "cd");
        ImmutableMessage message = new ImmutableMessage(1, list);
        messageActor.tell(message, ActorRef.noSender());
        //forward method
        messageActor.tell("forward", ActorRef.noSender());


        final Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS));
        //ask method
        Future<Object> rt = Patterns.ask(askActor, "request", timeout);
        try {
            String result = (String) Await.result(rt, timeout.duration());
            System.out.println(result);

        } catch (Exception e) {
            System.out.println(e);
        }



        final ArrayList<Future<Object>> futures = new ArrayList<Future<Object>>();
        futures.add(Patterns.ask(messageActor, "request", 1000));
        futures.add(Patterns.ask(askActor, "request1", 1000));

        final Future<Iterable<Object>> aggregate = Futures.sequence(futures, system.dispatcher());

        final Future<Result> transformed = aggregate.map(new Mapper<Iterable<Object>, Result>() {
            @Override
            public Result apply(final Iterable<Object> parameter) {
                final Iterator<Object> t = parameter.iterator();
                final String x = (String) t.next();
                final String y = (String) t.next();
                return new Result(x, y);
            }
        }, system.dispatcher());
        system.shutdown();

    }

    private static class Result {

        public Result(final String x, final String y) {

        }
    }
}
