package com.lerith.akka.chapter2.message;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by user on 2014/10/1.
 */
public class AskActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(final Object message) throws Exception {
        if (message.equals("request")) {
            Thread.sleep(1000);
            getSender().tell("success", ActorRef.noSender());
        } else {
            unhandled(message);
        }
    }
}
