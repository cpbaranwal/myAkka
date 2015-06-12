

import akka.actor.{ActorSystem, Actor, Props, ActorLogging}

case class ticket(quantity: Int)
case class fullPint(number: Int)
case class emptyPint(number: Int)


object HowdyAkka3 extends App
{
    val system = ActorSystem("HowdyAkka3")
    val bartender = system.actorOf(Props(new Bartender3), "bartender")
    val alice = system.actorOf(Props(new Person3), "alice")
    val bob = system.actorOf(Props(new Person3), "bob")
    val charlie = system.actorOf(Props(new Person3), "charlie")
    
    bartender.tell(ticket(2),alice)
    bartender.tell(ticket(1),bob)
    bartender.tell(ticket(3),charlie)
    system.awaitTermination()
}

class Person3 extends Actor with ActorLogging
{
    def receive =
    {
      case fullPint(n) => log.info(s"i am going to drink my pint number $n")
                          Thread.sleep(1000)
                          log.info(s"i i finished drinking my pint number $n")
                          sender ! emptyPint(n)
    }
}

class Bartender3 extends Actor with ActorLogging
{
  var total =0
    def receive =
    {
      case ticket(n)=> total=total+n
                       log.info(s"i am going to serve $n pints for Sir ${sender.path}")
                       for(i <- 1 to n)
                       {
                           log.info(s"Pint number $i is coming for Sir ${sender.path}")
                           Thread.sleep(1000)
                           log.info(s"Pint number $i is ready, here you go Mr ${sender.path}")
                           sender ! fullPint(i)
                       }
      case emptyPint(n)=> total match
        {
            case 1 => log.info("Thank you everyone, you all have finished with your pints, time to close shop, Good night!!!")
                      context.system.shutdown()
            case num => total= total-1
                        log.info(s"${sender.path} drank pint number $n , still there are $num pints remaining ")
        }
                       
    }
}