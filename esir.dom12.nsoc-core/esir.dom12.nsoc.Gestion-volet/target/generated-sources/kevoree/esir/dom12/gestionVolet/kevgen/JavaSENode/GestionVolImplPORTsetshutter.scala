package esir.dom12.gestionVolet.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.gestionVolet._
class GestionVolImplPORTsetshutter(component : GestionVolImpl) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "setshutter"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
