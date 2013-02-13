package esir.dom12.nsoc.module_gestion_dacces.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.module_gestion_dacces._
class ModuleGestionDaccesPORTADE_communication_entree(component : ModuleGestionDacces) extends esir.dom12.nsoc.donneesAde.Ade with KevoreeRequiredPort {
def getName : String = "ADE_communication_entree"
def getComponentName : String = component.getName 
def getInOut = true
def planningEtudiantParDate(arg0:scala.Int,arg1:scala.Int,arg2:scala.Int,arg3:java.lang.String) : Array[java.lang.String] ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("planningEtudiantParDate")
msgcall.getParams.put("arg0",arg0.asInstanceOf[AnyRef])
msgcall.getParams.put("arg1",arg1.asInstanceOf[AnyRef])
msgcall.getParams.put("arg2",arg2.asInstanceOf[AnyRef])
msgcall.getParams.put("arg3",arg3.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[Array[java.lang.String]]}
def planningSalleParDate(arg0:scala.Int,arg1:scala.Int,arg2:scala.Int,arg3:java.lang.String,arg4:java.lang.String) : Array[java.lang.String] ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("planningSalleParDate")
msgcall.getParams.put("arg0",arg0.asInstanceOf[AnyRef])
msgcall.getParams.put("arg1",arg1.asInstanceOf[AnyRef])
msgcall.getParams.put("arg2",arg2.asInstanceOf[AnyRef])
msgcall.getParams.put("arg3",arg3.asInstanceOf[AnyRef])
msgcall.getParams.put("arg4",arg4.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[Array[java.lang.String]]}
def coursActuelParEtudiant(arg0:java.lang.String) : java.lang.String ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("coursActuelParEtudiant")
msgcall.getParams.put("arg0",arg0.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[java.lang.String]}
def autorisation(arg0:java.lang.String) : scala.Boolean ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("autorisation")
msgcall.getParams.put("arg0",arg0.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[scala.Boolean]}
}
