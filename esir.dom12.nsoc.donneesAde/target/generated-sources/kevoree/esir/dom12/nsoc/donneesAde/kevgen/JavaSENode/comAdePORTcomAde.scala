package esir.dom12.nsoc.donneesAde.kevgen.JavaSENode
import org.kevoree.framework.port._
import esir.dom12.nsoc.donneesAde._
import scala.{Unit=>void}
class comAdePORTcomAde(component : comAde) extends esir.dom12.nsoc.donneesAde.Ade with KevoreeProvidedPort {
def getName : String = "comAde"
def getComponentName : String = component.getName 
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
override def internal_process(msg : Any)= msg match {
case opcall : org.kevoree.framework.MethodCallMessage => reply(opcall.getMethodName match {
case "planningSalleParDate"=> try { component.planningSalleParDate(if(opcall.getParams.containsKey("annee")){opcall.getParams.get("annee").asInstanceOf[scala.Int]}else{opcall.getParams.get("arg0").asInstanceOf[scala.Int]},if(opcall.getParams.containsKey("mois")){opcall.getParams.get("mois").asInstanceOf[scala.Int]}else{opcall.getParams.get("arg1").asInstanceOf[scala.Int]},if(opcall.getParams.containsKey("jour")){opcall.getParams.get("jour").asInstanceOf[scala.Int]}else{opcall.getParams.get("arg2").asInstanceOf[scala.Int]},if(opcall.getParams.containsKey("batiment")){opcall.getParams.get("batiment").asInstanceOf[java.lang.String]}else{opcall.getParams.get("arg3").asInstanceOf[java.lang.String]},if(opcall.getParams.containsKey("salle")){opcall.getParams.get("salle").asInstanceOf[java.lang.String]}else{opcall.getParams.get("arg4").asInstanceOf[java.lang.String]})} catch {case _ @ e => e.printStackTrace();println("Uncatched exception while processing Kevoree message");null }
case "coursActuelParEtudiant"=> try { component.coursActuelParEtudiant(if(opcall.getParams.containsKey("etudiant")){opcall.getParams.get("etudiant").asInstanceOf[java.lang.String]}else{opcall.getParams.get("arg0").asInstanceOf[java.lang.String]})} catch {case _ @ e => e.printStackTrace();println("Uncatched exception while processing Kevoree message");null }
case "planningEtudiantParDate"=> try { component.planningEtudiantParDate(if(opcall.getParams.containsKey("annee")){opcall.getParams.get("annee").asInstanceOf[scala.Int]}else{opcall.getParams.get("arg0").asInstanceOf[scala.Int]},if(opcall.getParams.containsKey("mois")){opcall.getParams.get("mois").asInstanceOf[scala.Int]}else{opcall.getParams.get("arg1").asInstanceOf[scala.Int]},if(opcall.getParams.containsKey("jour")){opcall.getParams.get("jour").asInstanceOf[scala.Int]}else{opcall.getParams.get("arg2").asInstanceOf[scala.Int]},if(opcall.getParams.containsKey("etudiant")){opcall.getParams.get("etudiant").asInstanceOf[java.lang.String]}else{opcall.getParams.get("arg3").asInstanceOf[java.lang.String]})} catch {case _ @ e => e.printStackTrace();println("Uncatched exception while processing Kevoree message");null }
case "autorisation"=> try { component.autorisation(if(opcall.getParams.containsKey("nom")){opcall.getParams.get("nom").asInstanceOf[java.lang.String]}else{opcall.getParams.get("arg0").asInstanceOf[java.lang.String]})} catch {case _ @ e => e.printStackTrace();println("Uncatched exception while processing Kevoree message");null }
case _ @ o => println("uncatch message , method not found in service declaration : "+o);null 
})}
}
