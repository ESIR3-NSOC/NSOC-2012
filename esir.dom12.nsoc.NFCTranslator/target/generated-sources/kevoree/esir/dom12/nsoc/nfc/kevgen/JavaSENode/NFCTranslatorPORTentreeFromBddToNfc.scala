package esir.dom12.nsoc.nfc.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.nfc._
import scala.{Unit=>void}
class NFCTranslatorPORTentreeFromBddToNfc(component : NFCTranslator) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "entreeFromBddToNfc"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.receiveNameFromConnexionBDD(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
