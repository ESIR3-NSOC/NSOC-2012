package esir.dom12.nsoc.scenarii.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.scenarii._
class ScenariiComponentPORTcommandRegul(component : ScenariiComponent) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "commandRegul"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
