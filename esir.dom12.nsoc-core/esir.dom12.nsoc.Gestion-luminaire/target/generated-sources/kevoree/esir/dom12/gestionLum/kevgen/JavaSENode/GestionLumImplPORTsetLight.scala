package esir.dom12.gestionLum.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.gestionLum._
class GestionLumImplPORTsetLight(component : GestionLumImpl) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "setLight"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
