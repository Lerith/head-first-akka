package com.lerith.akka.chapter2.message;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by user on 2014/10/1.
 */
public class MessageActor extends UntypedActor {
    private LoggingAdapter logger = Logging.getLogger(getContext().system(), this);


    ActorRef forwardActor;

    @Override
    public void preStart() throws Exception {
        super.preStart();
         forwardActor = getContext().actorOf(Props.create(ForwardActor.class), "forwardActor");
    }

    @Override
    public void onReceive(final Object message) throws Exception {

        if (message instanceof ImmutableMessage) {
            logger.info("Message:{}", message);
        } else if (message.equals("forward")) {
            logger.info("Message:{}", message);
            forwardActor.forward(message, getContext());
        } else {
            unhandled(message);
        }

    }
}
