package esir.dom12.nsoc.regulation.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.regulation._
class RegulationComponentPORTaskDataEquipment(component : RegulationComponent) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "askDataEquipment"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
