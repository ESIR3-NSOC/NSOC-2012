package esir.dom12.gestionVolet.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.gestionVolet._
class GestionVolImplFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=GestionVolImplFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=GestionVolImplFactory.remove(instanceName)
def createInstanceActivator = GestionVolImplFactory.createInstanceActivator
}

object GestionVolImplFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new GestionVolImplActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createGestionVolImpl()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.gestionVolet.GestionVolImpl].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.gestionVolet.GestionVolImpl].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.gestionVolet.GestionVolImpl].updateComponent()}
}}
def createGestionVolImpl() : esir.dom12.gestionVolet.GestionVolImpl ={
val newcomponent = new esir.dom12.gestionVolet.GestionVolImpl();
newcomponent.getHostedPorts().put("setshutterstate",createGestionVolImplPORTsetshutterstate(newcomponent))
newcomponent.getNeededPorts().put("setshutter",createGestionVolImplPORTsetshutter(newcomponent))
newcomponent}
def createGestionVolImplPORTsetshutterstate(component : GestionVolImpl) : GestionVolImplPORTsetshutterstate ={ new GestionVolImplPORTsetshutterstate(component)}
def createGestionVolImplPORTsetshutter(component : GestionVolImpl) : GestionVolImplPORTsetshutter ={ return new GestionVolImplPORTsetshutter(component);}
}
