package esir.dom12.nsoc.donneesAde.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.donneesAde._
class comAdeFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=comAdeFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=comAdeFactory.remove(instanceName)
def createInstanceActivator = comAdeFactory.createInstanceActivator
}

object comAdeFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new comAdeActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createcomAde()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.donneesAde.comAde].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.donneesAde.comAde].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.donneesAde.comAde].updateComponent()}
}}
def createcomAde() : esir.dom12.nsoc.donneesAde.comAde ={
val newcomponent = new esir.dom12.nsoc.donneesAde.comAde();
newcomponent.getHostedPorts().put("comAde",createcomAdePORTcomAde(newcomponent))
newcomponent}
def createcomAdePORTcomAde(component : comAde) : comAdePORTcomAde ={ new comAdePORTcomAde(component)}
}
