package esir.dom12.nsoc.testComAde.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.testComAde._
class testComAdeFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=testComAdeFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=testComAdeFactory.remove(instanceName)
def createInstanceActivator = testComAdeFactory.createInstanceActivator
}

object testComAdeFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new testComAdeActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createtestComAde()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.testComAde.testComAde].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.testComAde.testComAde].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.testComAde.testComAde].updateComponent()}
}}
def createtestComAde() : esir.dom12.nsoc.testComAde.testComAde ={
val newcomponent = new esir.dom12.nsoc.testComAde.testComAde();
newcomponent.getHostedPorts().put("output",createtestComAdePORToutput(newcomponent))
newcomponent.getNeededPorts().put("comAde",createtestComAdePORTcomAde(newcomponent))
newcomponent}
def createtestComAdePORToutput(component : testComAde) : testComAdePORToutput ={ new testComAdePORToutput(component)}
def createtestComAdePORTcomAde(component : testComAde) : testComAdePORTcomAde ={ return new testComAdePORTcomAde(component);}
}
