package esir.dom12.nsoc.nfc.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.nfc._
import scala.{Unit=>void}
class NFCTranslatorPORTentreeFromGaToNfc(component : NFCTranslator) extends esir.dom12.nsoc.nfc.NFCTranslatorInterface with KevoreeProvidedPort {
def getName : String = "entreeFromGaToNfc"
def getComponentName : String = component.getName 
def sendNumeroTagNFCFromGestionAccesToNfc(o:java.lang.Object) : void ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("sendNumeroTagNFCFromGestionAccesToNfc")
msgcall.getParams.put("o",o.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[void]}
override def internal_process(msg : Any)= msg match {
case opcall : org.kevoree.framework.MethodCallMessage => reply(opcall.getMethodName match {
case "sendNumeroTagNFCFromGestionAccesToNfc"=> try { component.receiveNumeroTagNFCFromGestionAcces(if(opcall.getParams.containsKey("o")){opcall.getParams.get("o").asInstanceOf[java.lang.Object]}else{opcall.getParams.get("arg0").asInstanceOf[java.lang.Object]})} catch {case _ @ e => e.printStackTrace();println("Uncatched exception while processing Kevoree message");null }
case _ @ o => println("uncatch message , method not found in service declaration : "+o);null 
})}
}
