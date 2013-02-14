package esir.dom12.nsoc.forcage_component.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.forcage_component._
class forcage_componentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=forcage_componentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=forcage_componentFactory.remove(instanceName)
def createInstanceActivator = forcage_componentFactory.createInstanceActivator
}

object forcage_componentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new forcage_componentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createforcage_component()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.forcage_component.forcage_component].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.forcage_component.forcage_component].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.forcage_component.forcage_component].updateComponent()}
}}
def createforcage_component() : esir.dom12.nsoc.forcage_component.forcage_component ={
val newcomponent = new esir.dom12.nsoc.forcage_component.forcage_component();
newcomponent.getHostedPorts().put("commandForcage",createforcage_componentPORTcommandForcage(newcomponent))
newcomponent.getNeededPorts().put("commandRegul",createforcage_componentPORTcommandRegul(newcomponent))
newcomponent.getNeededPorts().put("isSettingOFF",createforcage_componentPORTisSettingOFF(newcomponent))
newcomponent.getNeededPorts().put("isSettingON",createforcage_componentPORTisSettingON(newcomponent))
newcomponent}
def createforcage_componentPORTcommandForcage(component : forcage_component) : forcage_componentPORTcommandForcage ={ new forcage_componentPORTcommandForcage(component)}
def createforcage_componentPORTcommandRegul(component : forcage_component) : forcage_componentPORTcommandRegul ={ return new forcage_componentPORTcommandRegul(component);}
def createforcage_componentPORTisSettingOFF(component : forcage_component) : forcage_componentPORTisSettingOFF ={ return new forcage_componentPORTisSettingOFF(component);}
def createforcage_componentPORTisSettingON(component : forcage_component) : forcage_componentPORTisSettingON ={ return new forcage_componentPORTisSettingON(component);}
}
