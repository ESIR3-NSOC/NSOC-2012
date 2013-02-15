package esir.dom12.moduleKnx.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.moduleKnx._
class KnxImplementationPORTlog(component : KnxImplementation) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "log"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
