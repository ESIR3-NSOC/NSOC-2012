package esir.dom12.nsoc.nfc.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.nfc._
class NFCTranslatorPORTsortieFromNfcToBdd(component : NFCTranslator) extends esir.dom12.nsoc.nfc.ConnexionBDDInterface with KevoreeRequiredPort {
def getName : String = "sortieFromNfcToBdd"
def getComponentName : String = component.getName 
def getInOut = true
def sendRequestFromTrombiToBdd(req:java.lang.String) : javax.swing.ImageIcon ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("sendRequestFromTrombiToBdd")
msgcall.getParams.put("req",req.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[javax.swing.ImageIcon]}
def sendRequestFromNfcToBdd(req:java.lang.String) : java.lang.String ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("sendRequestFromNfcToBdd")
msgcall.getParams.put("req",req.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[java.lang.String]}
}
