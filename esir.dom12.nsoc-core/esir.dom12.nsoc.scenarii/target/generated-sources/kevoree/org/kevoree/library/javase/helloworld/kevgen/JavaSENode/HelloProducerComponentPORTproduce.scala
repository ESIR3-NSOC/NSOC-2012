package org.kevoree.library.javase.helloworld.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import org.kevoree.library.javase.helloworld._
class HelloProducerComponentPORTproduce(component : HelloProducerComponent) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "produce"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
