package com.lerith.akka.chapter2;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by user on 2014/8/26.
 */
public class MyUntypedActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(final Object message) throws Exception {
        if (message instanceof String) {
            log.info("Received String message:{}", message);
            getSender().tell(message, getSelf());
        } else {
            unhandled(message);
        }
    }
}
