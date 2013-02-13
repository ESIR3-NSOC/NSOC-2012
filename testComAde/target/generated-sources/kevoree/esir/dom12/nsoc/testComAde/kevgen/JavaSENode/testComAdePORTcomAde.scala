package esir.dom12.nsoc.testComAde.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.testComAde._
class testComAdePORTcomAde(component : testComAde) extends esir.dom12.nsoc.testComAde.Ade with KevoreeRequiredPort {
def getName : String = "comAde"
def getComponentName : String = component.getName 
def getInOut = true
def planningSalleParDate(annee:scala.Int,mois:scala.Int,jour:scala.Int,batiment:java.lang.String,salle:java.lang.String) : Array[java.lang.String] ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("planningSalleParDate")
msgcall.getParams.put("annee",annee.asInstanceOf[AnyRef])
msgcall.getParams.put("mois",mois.asInstanceOf[AnyRef])
msgcall.getParams.put("jour",jour.asInstanceOf[AnyRef])
msgcall.getParams.put("batiment",batiment.asInstanceOf[AnyRef])
msgcall.getParams.put("salle",salle.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[Array[java.lang.String]]}
def coursActuelParEtudiant(etudiant:java.lang.String) : java.lang.String ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("coursActuelParEtudiant")
msgcall.getParams.put("etudiant",etudiant.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[java.lang.String]}
def planningEtudiantParDate(annee:scala.Int,mois:scala.Int,jour:scala.Int,etudiant:java.lang.String) : Array[java.lang.String] ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("planningEtudiantParDate")
msgcall.getParams.put("annee",annee.asInstanceOf[AnyRef])
msgcall.getParams.put("mois",mois.asInstanceOf[AnyRef])
msgcall.getParams.put("jour",jour.asInstanceOf[AnyRef])
msgcall.getParams.put("etudiant",etudiant.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[Array[java.lang.String]]}
def autorisation(nom:java.lang.String) : scala.Boolean ={
val msgcall = new org.kevoree.framework.MethodCallMessage
msgcall.setMethodName("autorisation")
msgcall.getParams.put("nom",nom.asInstanceOf[AnyRef])
(this !? msgcall).asInstanceOf[scala.Boolean]}
}
