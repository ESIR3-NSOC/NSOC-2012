package esir.dom12.nsoc.regulation.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.regulation._
class RegulationComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=RegulationComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=RegulationComponentFactory.remove(instanceName)
def createInstanceActivator = RegulationComponentFactory.createInstanceActivator
}

object RegulationComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new RegulationComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createRegulationComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.regulation.RegulationComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.regulation.RegulationComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.regulation.RegulationComponent].updateComponent()}
}}
def createRegulationComponent() : esir.dom12.nsoc.regulation.RegulationComponent ={
val newcomponent = new esir.dom12.nsoc.regulation.RegulationComponent();
newcomponent.getHostedPorts().put("commandRegul",createRegulationComponentPORTcommandRegul(newcomponent))
newcomponent.getHostedPorts().put("commandKNX",createRegulationComponentPORTcommandKNX(newcomponent))
newcomponent.getNeededPorts().put("lightCommandRegulation",createRegulationComponentPORTlightCommandRegulation(newcomponent))
newcomponent.getNeededPorts().put("askDataEquipment",createRegulationComponentPORTaskDataEquipment(newcomponent))
newcomponent}
def createRegulationComponentPORTcommandRegul(component : RegulationComponent) : RegulationComponentPORTcommandRegul ={ new RegulationComponentPORTcommandRegul(component)}
def createRegulationComponentPORTcommandKNX(component : RegulationComponent) : RegulationComponentPORTcommandKNX ={ new RegulationComponentPORTcommandKNX(component)}
def createRegulationComponentPORTlightCommandRegulation(component : RegulationComponent) : RegulationComponentPORTlightCommandRegulation ={ return new RegulationComponentPORTlightCommandRegulation(component);}
def createRegulationComponentPORTaskDataEquipment(component : RegulationComponent) : RegulationComponentPORTaskDataEquipment ={ return new RegulationComponentPORTaskDataEquipment(component);}
}
