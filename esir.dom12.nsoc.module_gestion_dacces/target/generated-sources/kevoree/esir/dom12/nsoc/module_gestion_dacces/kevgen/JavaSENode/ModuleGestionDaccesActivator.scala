package esir.dom12.nsoc.module_gestion_dacces.kevgen.JavaSENode;
import java.util.Hashtable
import org.kevoree.api.service.core.handler.KevoreeModelHandlerService
import org.kevoree.framework.KevoreeActor
import org.kevoree.framework.KevoreeComponent
import org.kevoree.framework._
import esir.dom12.nsoc.module_gestion_dacces._
class ModuleGestionDaccesActivator extends org.kevoree.framework.osgi.KevoreeComponentActivator {
def callFactory() : KevoreeComponent = { ModuleGestionDaccesFactory.createComponentActor() } }
