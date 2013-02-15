package esir.dom12.nsoc.bdd.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.bdd._
class ConnexionBDDPORTtrombiSortie(component : ConnexionBDD) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "trombiSortie"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
