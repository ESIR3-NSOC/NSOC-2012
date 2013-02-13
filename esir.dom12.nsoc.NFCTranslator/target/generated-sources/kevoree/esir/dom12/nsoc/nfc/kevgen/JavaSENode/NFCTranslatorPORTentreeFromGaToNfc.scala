package esir.dom12.nsoc.nfc.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.nfc._
import scala.{Unit=>void}
class NFCTranslatorPORTentreeFromGaToNfc(component : NFCTranslator) extends esir.dom12.nsoc.nfc.NFCTranslatorInterface with KevoreeProvidedPort {
def getName : String = "entreeFromGaToNfc"
def getComponentName : String = component.getName 
def sendNumeroTagNFCFromGestionAccesToNfc(numeroTagNfc:java.lang.String) : java.lang.String ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("sendNumeroTagNFCFromGestionAccesToNfc")
msgcall.getParams.put("numeroTagNfc",numeroTagNfc.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[java.lang.String]}
override def internal_process(msg : Any)= msg match {
case opcall : org.kevoree.framework.MethodCallMessage => reply(opcall.getMethodName match {
case "sendNumeroTagNFCFromGestionAccesToNfc"=> try { component.sendNumeroTagNFCFromGestionAccesToNfc(if(opcall.getParams.containsKey("numeroTagNfc")){opcall.getParams.get("numeroTagNfc").asInstanceOf[java.lang.String]}else{opcall.getParams.get("arg0").asInstanceOf[java.lang.String]})} catch {case _ @ e => e.printStackTrace();println("Uncatched exception while processing Kevoree message");null }
case _ @ o => println("uncatch message , method not found in service declaration : "+o);null 
})}
}
