package esir.dom12.gestiondonne.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.gestiondonne._
class GestionDonneeFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=GestionDonneeFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=GestionDonneeFactory.remove(instanceName)
def createInstanceActivator = GestionDonneeFactory.createInstanceActivator
}

object GestionDonneeFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new GestionDonneeActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createGestionDonnee()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.gestiondonne.GestionDonnee].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.gestiondonne.GestionDonnee].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.gestiondonne.GestionDonnee].updateComponent()}
}}
def createGestionDonnee() : esir.dom12.gestiondonne.GestionDonnee ={
val newcomponent = new esir.dom12.gestiondonne.GestionDonnee();
newcomponent.getHostedPorts().put("getDataFromVincent",createGestionDonneePORTgetDataFromVincent(newcomponent))
newcomponent.getNeededPorts().put("getDataToKnx",createGestionDonneePORTgetDataToKnx(newcomponent))
newcomponent}
def createGestionDonneePORTgetDataFromVincent(component : GestionDonnee) : GestionDonneePORTgetDataFromVincent ={ new GestionDonneePORTgetDataFromVincent(component)}
def createGestionDonneePORTgetDataToKnx(component : GestionDonnee) : GestionDonneePORTgetDataToKnx ={ return new GestionDonneePORTgetDataToKnx(component);}
}
