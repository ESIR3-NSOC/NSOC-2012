package esir.dom12.gestionVolet.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.gestionVolet._
import scala.{Unit=>void}
class GestionVolImplPORTsetshutterstate(component : GestionVolImpl) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "setshutterstate"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.setEquipement(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
