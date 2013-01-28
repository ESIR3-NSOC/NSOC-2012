package esir.dom12.nsoc.nfc.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.nfc._
class NFCTranslatorPORTsortieFromNfcToGa(component : NFCTranslator) extends esir.dom12.nsoc.nfc.NFCTranslatorInterface with KevoreeRequiredPort {
def getName : String = "sortieFromNfcToGa"
def getComponentName : String = component.getName 
def getInOut = true
def sendNumeroTagNFCFromGestionAccesToNfc(o:java.lang.Object) : void ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("sendNumeroTagNFCFromGestionAccesToNfc")
msgcall.getParams.put("o",o.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[void]}
}
