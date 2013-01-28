package esir.dom12.nsoc.nfc.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.nfc._
class NFCTranslatorFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=NFCTranslatorFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=NFCTranslatorFactory.remove(instanceName)
def createInstanceActivator = NFCTranslatorFactory.createInstanceActivator
}

object NFCTranslatorFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new NFCTranslatorActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createNFCTranslator()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.nfc.NFCTranslator].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.nfc.NFCTranslator].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.nfc.NFCTranslator].updateComponent()}
}}
def createNFCTranslator() : esir.dom12.nsoc.nfc.NFCTranslator ={
val newcomponent = new esir.dom12.nsoc.nfc.NFCTranslator();
newcomponent.getHostedPorts().put("entreeFromGaToNfc",createNFCTranslatorPORTentreeFromGaToNfc(newcomponent))
newcomponent.getHostedPorts().put("entreeFromBddToNfc",createNFCTranslatorPORTentreeFromBddToNfc(newcomponent))
newcomponent.getNeededPorts().put("sortieFromNfcToGa",createNFCTranslatorPORTsortieFromNfcToGa(newcomponent))
newcomponent.getNeededPorts().put("sortieFromNfcToBdd",createNFCTranslatorPORTsortieFromNfcToBdd(newcomponent))
newcomponent}
def createNFCTranslatorPORTentreeFromGaToNfc(component : NFCTranslator) : NFCTranslatorPORTentreeFromGaToNfc ={ new NFCTranslatorPORTentreeFromGaToNfc(component)}
def createNFCTranslatorPORTentreeFromBddToNfc(component : NFCTranslator) : NFCTranslatorPORTentreeFromBddToNfc ={ new NFCTranslatorPORTentreeFromBddToNfc(component)}
def createNFCTranslatorPORTsortieFromNfcToGa(component : NFCTranslator) : NFCTranslatorPORTsortieFromNfcToGa ={ return new NFCTranslatorPORTsortieFromNfcToGa(component);}
def createNFCTranslatorPORTsortieFromNfcToBdd(component : NFCTranslator) : NFCTranslatorPORTsortieFromNfcToBdd ={ return new NFCTranslatorPORTsortieFromNfcToBdd(component);}
}
