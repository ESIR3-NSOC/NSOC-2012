package esir.dom12.nsoc.nfc.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.nfc._
class NFCTranslatorPORTsortieFromNfcToBdd(component : NFCTranslator) extends esir.dom12.nsoc.bdd.ConnexionBDDInterface with KevoreeRequiredPort {
def getName : String = "sortieFromNfcToBdd"
def getComponentName : String = component.getName 
def getInOut = true
def sendRequestFromNfcToBdd(arg0:java.lang.String) : java.lang.String ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("sendRequestFromNfcToBdd")
msgcall.getParams.put("arg0",arg0.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[java.lang.String]}
}
