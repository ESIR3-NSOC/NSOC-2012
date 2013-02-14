package esir.dom12.nsoc.module_gestion_dacces.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.module_gestion_dacces._
import scala.{Unit=>void}
class ModuleGestionDaccesPORTidentification_sortie(component : ModuleGestionDacces) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "identification_sortie"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.traitement_message(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
