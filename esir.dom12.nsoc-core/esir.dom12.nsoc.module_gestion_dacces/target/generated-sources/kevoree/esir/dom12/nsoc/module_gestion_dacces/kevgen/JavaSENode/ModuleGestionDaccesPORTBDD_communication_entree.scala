package esir.dom12.nsoc.module_gestion_dacces.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.module_gestion_dacces._
class ModuleGestionDaccesPORTBDD_communication_entree(component : ModuleGestionDacces) extends esir.dom12.nsoc.nfc.NFCTranslatorInterface with KevoreeRequiredPort {
def getName : String = "BDD_communication_entree"
def getComponentName : String = component.getName 
def getInOut = true
def sendNumeroTagNFCFromGestionAccesToNfc(arg0:java.lang.String) : java.lang.String ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("sendNumeroTagNFCFromGestionAccesToNfc")
msgcall.getParams.put("arg0",arg0.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[java.lang.String]}
}
