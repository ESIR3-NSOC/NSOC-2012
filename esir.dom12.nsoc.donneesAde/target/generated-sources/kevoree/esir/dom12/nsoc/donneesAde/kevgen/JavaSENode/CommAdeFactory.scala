package esir.dom12.nsoc.donneesAde.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.donneesAde._
class CommAdeFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=CommAdeFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=CommAdeFactory.remove(instanceName)
def createInstanceActivator = CommAdeFactory.createInstanceActivator
}

object CommAdeFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new CommAdeActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createCommAde()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.donneesAde.CommAde].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.donneesAde.CommAde].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.donneesAde.CommAde].updateComponent()}
}}
def createCommAde() : esir.dom12.nsoc.donneesAde.CommAde ={
val newcomponent = new esir.dom12.nsoc.donneesAde.CommAde();
newcomponent.getHostedPorts().put("consume",createCommAdePORTconsume(newcomponent))
newcomponent}
def createCommAdePORTconsume(component : CommAde) : CommAdePORTconsume ={ new CommAdePORTconsume(component)}
}
