package com.lerith.akka.chapter2

import akka.actor.Actor
import akka.event.Logging

/**
 * Created by user on 2014/8/26.
 */
class MyActor extends Actor {
  val log = Logging(context.system, this);

  def receive = {
    case "test" => log.info("received test")
    case _ => log.info("received unknown message")
  }
}
