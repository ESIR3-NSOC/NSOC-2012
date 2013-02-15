package esir.dom12.gestionLum.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.gestionLum._
class GestionLumImplFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=GestionLumImplFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=GestionLumImplFactory.remove(instanceName)
def createInstanceActivator = GestionLumImplFactory.createInstanceActivator
}

object GestionLumImplFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new GestionLumImplActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createGestionLumImpl()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.gestionLum.GestionLumImpl].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.gestionLum.GestionLumImpl].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.gestionLum.GestionLumImpl].updateComponent()}
}}
def createGestionLumImpl() : esir.dom12.gestionLum.GestionLumImpl ={
val newcomponent = new esir.dom12.gestionLum.GestionLumImpl();
newcomponent.getHostedPorts().put("setLightState",createGestionLumImplPORTsetLightState(newcomponent))
newcomponent.getNeededPorts().put("setLight",createGestionLumImplPORTsetLight(newcomponent))
newcomponent}
def createGestionLumImplPORTsetLightState(component : GestionLumImpl) : GestionLumImplPORTsetLightState ={ new GestionLumImplPORTsetLightState(component)}
def createGestionLumImplPORTsetLight(component : GestionLumImpl) : GestionLumImplPORTsetLight ={ return new GestionLumImplPORTsetLight(component);}
}
