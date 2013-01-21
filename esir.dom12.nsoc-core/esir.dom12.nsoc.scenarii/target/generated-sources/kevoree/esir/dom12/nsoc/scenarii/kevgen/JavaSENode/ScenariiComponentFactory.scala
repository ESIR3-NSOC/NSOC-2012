package esir.dom12.nsoc.scenarii.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.scenarii._
class ScenariiComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=ScenariiComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=ScenariiComponentFactory.remove(instanceName)
def createInstanceActivator = ScenariiComponentFactory.createInstanceActivator
}

object ScenariiComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new ScenariiComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createScenariiComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.scenarii.ScenariiComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.scenarii.ScenariiComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.scenarii.ScenariiComponent].updateComponent()}
}}
def createScenariiComponent() : esir.dom12.nsoc.scenarii.ScenariiComponent ={
val newcomponent = new esir.dom12.nsoc.scenarii.ScenariiComponent();
newcomponent.getHostedPorts().put("commandScenarii",createScenariiComponentPORTcommandScenarii(newcomponent))
newcomponent.getNeededPorts().put("commandRegul",createScenariiComponentPORTcommandRegul(newcomponent))
newcomponent.getNeededPorts().put("isSettingON",createScenariiComponentPORTisSettingON(newcomponent))
newcomponent.getNeededPorts().put("isSettingOFF",createScenariiComponentPORTisSettingOFF(newcomponent))
newcomponent}
def createScenariiComponentPORTcommandScenarii(component : ScenariiComponent) : ScenariiComponentPORTcommandScenarii ={ new ScenariiComponentPORTcommandScenarii(component)}
def createScenariiComponentPORTcommandRegul(component : ScenariiComponent) : ScenariiComponentPORTcommandRegul ={ return new ScenariiComponentPORTcommandRegul(component);}
def createScenariiComponentPORTisSettingON(component : ScenariiComponent) : ScenariiComponentPORTisSettingON ={ return new ScenariiComponentPORTisSettingON(component);}
def createScenariiComponentPORTisSettingOFF(component : ScenariiComponent) : ScenariiComponentPORTisSettingOFF ={ return new ScenariiComponentPORTisSettingOFF(component);}
}
