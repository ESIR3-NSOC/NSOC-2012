package org.kevoree.library.javase.helloworld.kevgen.JavaSENode
import org.kevoree.framework._
import org.kevoree.library.javase.helloworld._
class HelloProducerComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=HelloProducerComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=HelloProducerComponentFactory.remove(instanceName)
def createInstanceActivator = HelloProducerComponentFactory.createInstanceActivator
}

object HelloProducerComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new HelloProducerComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createHelloProducerComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.helloworld.HelloProducerComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.helloworld.HelloProducerComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.helloworld.HelloProducerComponent].updateComponent()}
}}
def createHelloProducerComponent() : org.kevoree.library.javase.helloworld.HelloProducerComponent ={
val newcomponent = new org.kevoree.library.javase.helloworld.HelloProducerComponent();
newcomponent.getNeededPorts().put("produce",createHelloProducerComponentPORTproduce(newcomponent))
newcomponent}
def createHelloProducerComponentPORTproduce(component : HelloProducerComponent) : HelloProducerComponentPORTproduce ={ return new HelloProducerComponentPORTproduce(component);}
}
