package esir.dom12.nsoc.video.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.video._
import scala.{Unit=>void}
class VideoComponentPORTcommandVideo(component : VideoComponent) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "commandVideo"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.receiveCommandVideo(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
