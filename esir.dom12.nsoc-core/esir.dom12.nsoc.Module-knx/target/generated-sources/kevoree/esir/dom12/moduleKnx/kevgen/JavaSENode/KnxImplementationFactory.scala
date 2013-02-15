package esir.dom12.moduleKnx.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.moduleKnx._
class KnxImplementationFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=KnxImplementationFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=KnxImplementationFactory.remove(instanceName)
def createInstanceActivator = KnxImplementationFactory.createInstanceActivator
}

object KnxImplementationFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new KnxImplementationActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createKnxImplementation()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.moduleKnx.KnxImplementation].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.moduleKnx.KnxImplementation].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.moduleKnx.KnxImplementation].updateComponent()}
}}
def createKnxImplementation() : esir.dom12.moduleKnx.KnxImplementation ={
val newcomponent = new esir.dom12.moduleKnx.KnxImplementation();
newcomponent.getHostedPorts().put("setEquipementState",createKnxImplementationPORTsetEquipementState(newcomponent))
newcomponent.getHostedPorts().put("getEquipementState",createKnxImplementationPORTgetEquipementState(newcomponent))
newcomponent.getNeededPorts().put("getData",createKnxImplementationPORTgetData(newcomponent))
newcomponent.getNeededPorts().put("log",createKnxImplementationPORTlog(newcomponent))
newcomponent}
def createKnxImplementationPORTsetEquipementState(component : KnxImplementation) : KnxImplementationPORTsetEquipementState ={ new KnxImplementationPORTsetEquipementState(component)}
def createKnxImplementationPORTgetEquipementState(component : KnxImplementation) : KnxImplementationPORTgetEquipementState ={ new KnxImplementationPORTgetEquipementState(component)}
def createKnxImplementationPORTgetData(component : KnxImplementation) : KnxImplementationPORTgetData ={ return new KnxImplementationPORTgetData(component);}
def createKnxImplementationPORTlog(component : KnxImplementation) : KnxImplementationPORTlog ={ return new KnxImplementationPORTlog(component);}
}
