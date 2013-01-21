package esir.dom12.nsoc.nfc.kevgen.JavaSENode
import org.kevoree.framework.port._
import scala.{Unit=>void}
import esir.dom12.nsoc.nfc._
class NFCTranslatorPORTsortieFromNfcToBdd(component : NFCTranslator) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "sortieFromNfcToBdd"
def getComponentName : String = component.getName 
def process(o : Object) = {
{this ! o}
}
def getInOut = false
}
