package esir.dom12.nsoc.forcage_component.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.forcage_component._
class forcage_componentPORTisSettingON(component : forcage_component) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "isSettingON"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}