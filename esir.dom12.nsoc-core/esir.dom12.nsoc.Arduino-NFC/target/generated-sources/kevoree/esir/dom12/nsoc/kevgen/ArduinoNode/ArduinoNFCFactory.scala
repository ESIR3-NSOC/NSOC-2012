package esir.dom12.nsoc.kevgen.ArduinoNode
import org.kevoree.framework._
import esir.dom12.nsoc._
class ArduinoNFCFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=ArduinoNFCFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=ArduinoNFCFactory.remove(instanceName)
def createInstanceActivator = ArduinoNFCFactory.createInstanceActivator
}

object ArduinoNFCFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new ArduinoNFCActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createArduinoNFC()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.ArduinoNFC].dummy()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.ArduinoNFC].dummy()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.ArduinoNFC].dummy()}
}}
def createArduinoNFC() : esir.dom12.nsoc.ArduinoNFC ={
val newcomponent = new esir.dom12.nsoc.ArduinoNFC();
newcomponent.getNeededPorts().put("serial",createArduinoNFCPORTserial(newcomponent))
newcomponent}
def createArduinoNFCPORTserial(component : ArduinoNFC) : ArduinoNFCPORTserial ={ return new ArduinoNFCPORTserial(component);}
}
