package esir.dom12.gestiondonne.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.gestiondonne._
import scala.{Unit=>void}
class GestionDonneePORTgetDataFromVincent(component : GestionDonnee) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "getDataFromVincent"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.getData(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
