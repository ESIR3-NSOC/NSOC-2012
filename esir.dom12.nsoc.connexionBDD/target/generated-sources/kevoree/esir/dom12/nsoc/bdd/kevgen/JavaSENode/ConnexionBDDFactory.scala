package esir.dom12.nsoc.bdd.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.bdd._
class ConnexionBDDFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=ConnexionBDDFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=ConnexionBDDFactory.remove(instanceName)
def createInstanceActivator = ConnexionBDDFactory.createInstanceActivator
}

object ConnexionBDDFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new ConnexionBDDActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createConnexionBDD()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.bdd.ConnexionBDD].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.bdd.ConnexionBDD].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.bdd.ConnexionBDD].updateComponent()}
}}
def createConnexionBDD() : esir.dom12.nsoc.bdd.ConnexionBDD ={
val newcomponent = new esir.dom12.nsoc.bdd.ConnexionBDD();
newcomponent.getHostedPorts().put("entreeFromNfcToBdd",createConnexionBDDPORTentreeFromNfcToBdd(newcomponent))
newcomponent.getNeededPorts().put("sortieFromBddToNfc",createConnexionBDDPORTsortieFromBddToNfc(newcomponent))
newcomponent}
def createConnexionBDDPORTentreeFromNfcToBdd(component : ConnexionBDD) : ConnexionBDDPORTentreeFromNfcToBdd ={ new ConnexionBDDPORTentreeFromNfcToBdd(component)}
def createConnexionBDDPORTsortieFromBddToNfc(component : ConnexionBDD) : ConnexionBDDPORTsortieFromBddToNfc ={ return new ConnexionBDDPORTsortieFromBddToNfc(component);}
}