package esir.dom12.nsoc.video.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.video._
class VideoComponentPORTsetshutter(component : VideoComponent) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "setshutter"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
