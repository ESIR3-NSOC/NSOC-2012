package esir.dom12.nsoc.donneesAde.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.donneesAde._
import scala.{Unit=>void}
class CommAdePORTconsume(component : CommAde) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "consume"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.consumeHello(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
