package esir.dom12.nsoc.bdd.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.bdd._
import scala.{Unit=>void}
class ConnexionBDDPORTTrombi(component : ConnexionBDD) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "Trombi"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.sendRequestFromTrombiToBdd(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
