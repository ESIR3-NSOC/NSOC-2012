package org.kevoree.library.javase.helloworld.kevgen.JavaSENode
import org.kevoree.framework._
import org.kevoree.library.javase.helloworld._
class HelloConsumerComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=HelloConsumerComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=HelloConsumerComponentFactory.remove(instanceName)
def createInstanceActivator = HelloConsumerComponentFactory.createInstanceActivator
}

object HelloConsumerComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new HelloConsumerComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createHelloConsumerComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.helloworld.HelloConsumerComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.helloworld.HelloConsumerComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.library.javase.helloworld.HelloConsumerComponent].updateComponent()}
}}
def createHelloConsumerComponent() : org.kevoree.library.javase.helloworld.HelloConsumerComponent ={
val newcomponent = new org.kevoree.library.javase.helloworld.HelloConsumerComponent();
newcomponent.getHostedPorts().put("consume",createHelloConsumerComponentPORTconsume(newcomponent))
newcomponent}
def createHelloConsumerComponentPORTconsume(component : HelloConsumerComponent) : HelloConsumerComponentPORTconsume ={ new HelloConsumerComponentPORTconsume(component)}
}
