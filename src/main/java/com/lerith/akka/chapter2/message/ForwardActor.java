package com.lerith.akka.chapter2.message;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by user on 2014/10/1.
 */
public class ForwardActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(final Object message) throws Exception {

        if (message.equals("forward")) {
            log.info("FowardActor Message:{}", message);
            log.info("GeSender:{}",getSender().toString());
        } else {
            unhandled(message);

        }

    }
}
