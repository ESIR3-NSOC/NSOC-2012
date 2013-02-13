package esir.dom12.nsoc.testComAde.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.testComAde._
import scala.{Unit=>void}
class testComAdePORToutput(component : testComAde) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "output"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.test(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
