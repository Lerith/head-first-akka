package com.lerith.akka.chapter2.message;

import akka.actor.ReceiveTimeout;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2014/10/1.
 */
public class ReceiveTimeoutUntypedActor extends UntypedActor {
    private LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    @Override
    public void preStart() throws Exception {
        super.preStart();
        getContext().setReceiveTimeout(Duration.create(30, TimeUnit.SECONDS));
    }

    @Override
    public void onReceive(final Object message) throws Exception {
        logger.info("ReceiveTimeoutUntypedActor:{}", message);
        if (message.equals("Timeout")) {

            getContext().setReceiveTimeout(Duration.create(1, TimeUnit.SECONDS));
        } else if (message instanceof ReceiveTimeout) {
            getContext().setReceiveTimeout(Duration.Undefined());
        } else {
            unhandled(message);
        }
    }
}
