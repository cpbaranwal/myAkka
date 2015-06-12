

/**
 * @author chandan
 */

import akka.actor.{ActorSystem, ActorLogging, Actor, Props}

case object Pint

class Person extends Actor with ActorLogging {
  def receive = {
    case Pint => log.info("Thanks for the pint")
  }
}

object HowdyAkka extends App
{
  println("Howdy Akka")
  val system = ActorSystem("howdy-akka")
  val alice = system.actorOf(Props(new Person), "alice")
  alice ! Pint
  system.shutdown()
}