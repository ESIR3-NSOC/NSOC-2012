package esir.dom12.nsoc.donneesAAfficher.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.donneesAAfficher._
class donneesAAfficherFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=donneesAAfficherFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=donneesAAfficherFactory.remove(instanceName)
def createInstanceActivator = donneesAAfficherFactory.createInstanceActivator
}

object donneesAAfficherFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new donneesAAfficherActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createdonneesAAfficher()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.donneesAAfficher.donneesAAfficher].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.donneesAAfficher.donneesAAfficher].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.donneesAAfficher.donneesAAfficher].updateComponent()}
}}
def createdonneesAAfficher() : esir.dom12.nsoc.donneesAAfficher.donneesAAfficher ={
val newcomponent = new esir.dom12.nsoc.donneesAAfficher.donneesAAfficher();
newcomponent.getHostedPorts().put("recupDateHeure",createdonneesAAfficherPORTrecupDateHeure(newcomponent))
newcomponent.getHostedPorts().put("occupation",createdonneesAAfficherPORToccupation(newcomponent))
newcomponent.getHostedPorts().put("entreeADE",createdonneesAAfficherPORTentreeADE(newcomponent))
newcomponent.getHostedPorts().put("entreeADEPlusUn",createdonneesAAfficherPORTentreeADEPlusUn(newcomponent))
newcomponent.getNeededPorts().put("comAde",createdonneesAAfficherPORTcomAde(newcomponent))
newcomponent}
def createdonneesAAfficherPORTrecupDateHeure(component : donneesAAfficher) : donneesAAfficherPORTrecupDateHeure ={ new donneesAAfficherPORTrecupDateHeure(component)}
def createdonneesAAfficherPORToccupation(component : donneesAAfficher) : donneesAAfficherPORToccupation ={ new donneesAAfficherPORToccupation(component)}
def createdonneesAAfficherPORTentreeADE(component : donneesAAfficher) : donneesAAfficherPORTentreeADE ={ new donneesAAfficherPORTentreeADE(component)}
def createdonneesAAfficherPORTentreeADEPlusUn(component : donneesAAfficher) : donneesAAfficherPORTentreeADEPlusUn ={ new donneesAAfficherPORTentreeADEPlusUn(component)}
def createdonneesAAfficherPORTcomAde(component : donneesAAfficher) : donneesAAfficherPORTcomAde ={ return new donneesAAfficherPORTcomAde(component);}
}
