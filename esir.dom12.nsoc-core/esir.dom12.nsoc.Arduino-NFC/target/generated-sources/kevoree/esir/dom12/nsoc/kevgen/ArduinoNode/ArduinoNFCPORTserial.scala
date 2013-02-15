package esir.dom12.nsoc.kevgen.ArduinoNode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc._
class ArduinoNFCPORTserial(component : ArduinoNFC) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "serial"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
