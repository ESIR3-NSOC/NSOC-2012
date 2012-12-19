package org.kevoree.library.javase.helloworld.kevgen.JavaSENode
import org.kevoree.framework.port._
import org.kevoree.library.javase.helloworld._
import scala.{Unit=>void}
class HelloConsumerComponentPORTconsume(component : HelloConsumerComponent) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "consume"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)= msg match {
case _ @ msg =>try{component.consumeHello(msg)}catch{case _ @ e => {e.printStackTrace();println("Uncatched exception while processing Kevoree message")}}
}
}
