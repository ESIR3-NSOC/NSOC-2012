package esir.dom12.moduleKnx.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.moduleKnx._
import scala.{Unit=>void}
class KnxImplementationPORTsetEquipementState(component : KnxImplementation) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "setEquipementState"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.setComposant(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
