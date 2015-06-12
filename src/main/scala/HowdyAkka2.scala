
import akka.actor.{ActorSystem,ActorLogging,Actor,Props}

case object Ticket
case object FullPint
case object EmptyPint

class Bartender extends Actor with ActorLogging
{
    def receive = 
    {
      case Ticket => Thread.sleep(1000)
                     log.info("1 pint coming up")
                     Thread.sleep(4000)
                     log.info("your pint is ready sir, here you go")
                     sender ! FullPint
                     
      case EmptyPint => Thread.sleep(2000)
                        log.info("Sorry sir, only 1 pint alllowed...u r done for the day")
                        context.system.shutdown()
    }
}

class Person2 extends Actor with ActorLogging
{
     def receive =
      {
       case FullPint =>  Thread.sleep(2000)
                         log.info("Thank you for the pint, i will drink it!")
                         Thread.sleep(4000)
                         log.info(" Can i have another one now.....? i am ready for next pint")
                         sender ! EmptyPint
      }
}

object HowdyAkka2 extends App
{
 println("in HowdyAkka2   11111111")
 val system2 = ActorSystem("HowdyAkka2")
 val bartender = system2.actorOf(Props(new Bartender), "bartender")
 val person2 = system2.actorOf(Props(new Person2), "person")
 bartender.tell(Ticket,person2)
 system2.awaitTermination()

}
 