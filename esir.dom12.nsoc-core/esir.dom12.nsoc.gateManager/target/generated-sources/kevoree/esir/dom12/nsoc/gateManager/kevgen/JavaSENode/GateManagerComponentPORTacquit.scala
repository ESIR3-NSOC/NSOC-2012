package esir.dom12.nsoc.gateManager.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.gateManager._
class GateManagerComponentPORTacquit(component : GateManagerComponent) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "acquit"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
