package esir.dom12.gestionLum.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.gestionLum._
import scala.{Unit=>void}
class GestionLumImplPORTsetLightState(component : GestionLumImpl) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "setLightState"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.setEquipement(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
