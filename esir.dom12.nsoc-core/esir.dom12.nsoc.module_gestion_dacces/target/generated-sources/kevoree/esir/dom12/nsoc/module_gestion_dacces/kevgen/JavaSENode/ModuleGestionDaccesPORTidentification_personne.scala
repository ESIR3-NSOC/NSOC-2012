package esir.dom12.nsoc.module_gestion_dacces.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.module_gestion_dacces._
class ModuleGestionDaccesPORTidentification_personne(component : ModuleGestionDacces) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "identification_personne"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
