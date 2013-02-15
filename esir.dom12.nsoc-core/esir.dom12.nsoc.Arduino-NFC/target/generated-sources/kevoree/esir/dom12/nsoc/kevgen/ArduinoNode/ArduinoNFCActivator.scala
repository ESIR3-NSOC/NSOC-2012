package esir.dom12.nsoc.kevgen.ArduinoNode;
import java.util.Hashtable
import org.kevoree.api.service.core.handler.KevoreeModelHandlerService
import org.kevoree.framework.KevoreeActor
import org.kevoree.framework.KevoreeComponent
import org.kevoree.framework._
import esir.dom12.nsoc._
class ArduinoNFCActivator extends org.kevoree.framework.osgi.KevoreeComponentActivator {
def callFactory() : KevoreeComponent = { ArduinoNFCFactory.createComponentActor() } }
