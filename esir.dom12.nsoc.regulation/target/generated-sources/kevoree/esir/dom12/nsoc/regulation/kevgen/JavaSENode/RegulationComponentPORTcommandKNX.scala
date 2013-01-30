package esir.dom12.nsoc.regulation.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.regulation._
import scala.{Unit=>void}
class RegulationComponentPORTcommandKNX(component : RegulationComponent) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "commandKNX"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.receiveValueKNX(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
