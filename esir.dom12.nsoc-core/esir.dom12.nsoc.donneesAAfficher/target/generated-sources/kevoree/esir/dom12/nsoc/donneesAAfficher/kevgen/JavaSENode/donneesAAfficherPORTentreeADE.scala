package esir.dom12.nsoc.donneesAAfficher.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.donneesAAfficher._
import scala.{Unit=>void}
class donneesAAfficherPORTentreeADE(component : donneesAAfficher) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "entreeADE"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.emploiDuTemps(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
