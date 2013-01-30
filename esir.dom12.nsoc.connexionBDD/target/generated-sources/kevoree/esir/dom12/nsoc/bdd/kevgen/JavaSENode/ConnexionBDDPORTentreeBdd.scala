package esir.dom12.nsoc.bdd.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.bdd._
import scala.{Unit=>void}
class ConnexionBDDPORTentreeBdd(component : ConnexionBDD) extends esir.dom12.nsoc.bdd.ConnexionBDDInterface with KevoreeProvidedPort {
def getName : String = "entreeBdd"
def getComponentName : String = component.getName 
def sendRequestFromNfcToBdd(req:java.lang.String) : java.lang.String ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("sendRequestFromNfcToBdd")
msgcall.getParams.put("req",req.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[java.lang.String]}
def sendRequestFromTrombiToBdd(req:java.lang.String) : java.lang.String ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("sendRequestFromTrombiToBdd")
msgcall.getParams.put("req",req.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[java.lang.String]}
override def internal_process(msg : Any)= msg match {
case opcall : org.kevoree.framework.MethodCallMessage => reply(opcall.getMethodName match {
case "sendRequestFromNfcToBdd"=> try { component.sendRequestFromNfcToBdd(if(opcall.getParams.containsKey("req")){opcall.getParams.get("req").asInstanceOf[java.lang.String]}else{opcall.getParams.get("arg0").asInstanceOf[java.lang.String]})} catch {case _ @ e => e.printStackTrace();println("Uncatched exception while processing Kevoree message");null }
case "sendRequestFromTrombiToBdd"=> try { component.sendRequestFromTrombiToBdd(if(opcall.getParams.containsKey("req")){opcall.getParams.get("req").asInstanceOf[java.lang.String]}else{opcall.getParams.get("arg0").asInstanceOf[java.lang.String]})} catch {case _ @ e => e.printStackTrace();println("Uncatched exception while processing Kevoree message");null }
case _ @ o => println("uncatch message , method not found in service declaration : "+o);null 
})}
}
