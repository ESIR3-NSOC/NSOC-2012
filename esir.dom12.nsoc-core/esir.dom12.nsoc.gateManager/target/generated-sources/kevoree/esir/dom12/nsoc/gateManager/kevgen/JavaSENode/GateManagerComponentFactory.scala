package esir.dom12.nsoc.gateManager.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.gateManager._
class GateManagerComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=GateManagerComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=GateManagerComponentFactory.remove(instanceName)
def createInstanceActivator = GateManagerComponentFactory.createInstanceActivator
}

object GateManagerComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new GateManagerComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createGateManagerComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.gateManager.GateManagerComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.gateManager.GateManagerComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.gateManager.GateManagerComponent].updateComponent()}
}}
def createGateManagerComponent() : esir.dom12.nsoc.gateManager.GateManagerComponent ={
val newcomponent = new esir.dom12.nsoc.gateManager.GateManagerComponent();
newcomponent.getHostedPorts().put("door",createGateManagerComponentPORTdoor(newcomponent))
newcomponent.getNeededPorts().put("acquit",createGateManagerComponentPORTacquit(newcomponent))
newcomponent}
def createGateManagerComponentPORTdoor(component : GateManagerComponent) : GateManagerComponentPORTdoor ={ new GateManagerComponentPORTdoor(component)}
def createGateManagerComponentPORTacquit(component : GateManagerComponent) : GateManagerComponentPORTacquit ={ return new GateManagerComponentPORTacquit(component);}
}
