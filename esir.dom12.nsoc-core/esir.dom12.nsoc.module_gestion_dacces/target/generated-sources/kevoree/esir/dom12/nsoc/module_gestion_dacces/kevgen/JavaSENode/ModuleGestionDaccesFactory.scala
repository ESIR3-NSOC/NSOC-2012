package esir.dom12.nsoc.module_gestion_dacces.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.module_gestion_dacces._
class ModuleGestionDaccesFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=ModuleGestionDaccesFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=ModuleGestionDaccesFactory.remove(instanceName)
def createInstanceActivator = ModuleGestionDaccesFactory.createInstanceActivator
}

object ModuleGestionDaccesFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new ModuleGestionDaccesActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createModuleGestionDacces()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.module_gestion_dacces.ModuleGestionDacces].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.module_gestion_dacces.ModuleGestionDacces].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.module_gestion_dacces.ModuleGestionDacces].updateComponent()}
}}
def createModuleGestionDacces() : esir.dom12.nsoc.module_gestion_dacces.ModuleGestionDacces ={
val newcomponent = new esir.dom12.nsoc.module_gestion_dacces.ModuleGestionDacces();
newcomponent.getHostedPorts().put("entree_test",createModuleGestionDaccesPORTentree_test(newcomponent))
newcomponent.getHostedPorts().put("identification_sortie",createModuleGestionDaccesPORTidentification_sortie(newcomponent))
newcomponent.getNeededPorts().put("BDD_communication_entree",createModuleGestionDaccesPORTBDD_communication_entree(newcomponent))
newcomponent.getNeededPorts().put("ADE_communication_entree",createModuleGestionDaccesPORTADE_communication_entree(newcomponent))
newcomponent.getNeededPorts().put("identification_entree",createModuleGestionDaccesPORTidentification_entree(newcomponent))
newcomponent}
def createModuleGestionDaccesPORTentree_test(component : ModuleGestionDacces) : ModuleGestionDaccesPORTentree_test ={ new ModuleGestionDaccesPORTentree_test(component)}
def createModuleGestionDaccesPORTidentification_sortie(component : ModuleGestionDacces) : ModuleGestionDaccesPORTidentification_sortie ={ new ModuleGestionDaccesPORTidentification_sortie(component)}
def createModuleGestionDaccesPORTBDD_communication_entree(component : ModuleGestionDacces) : ModuleGestionDaccesPORTBDD_communication_entree ={ return new ModuleGestionDaccesPORTBDD_communication_entree(component);}
def createModuleGestionDaccesPORTADE_communication_entree(component : ModuleGestionDacces) : ModuleGestionDaccesPORTADE_communication_entree ={ return new ModuleGestionDaccesPORTADE_communication_entree(component);}
def createModuleGestionDaccesPORTidentification_entree(component : ModuleGestionDacces) : ModuleGestionDaccesPORTidentification_entree ={ return new ModuleGestionDaccesPORTidentification_entree(component);}
}
