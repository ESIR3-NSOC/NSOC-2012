package esir.dom12.gestiondonne.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.gestiondonne._
class GestionDonneePORTgetDataToKnx(component : GestionDonnee) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "getDataToKnx"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
