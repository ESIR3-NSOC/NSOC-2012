package esir.dom12.nsoc.forcage_component.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.forcage_component._
import scala.{Unit=>void}
class forcage_componentPORTcommandForcage(component : forcage_component) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "commandForcage"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.receiveMessage(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
