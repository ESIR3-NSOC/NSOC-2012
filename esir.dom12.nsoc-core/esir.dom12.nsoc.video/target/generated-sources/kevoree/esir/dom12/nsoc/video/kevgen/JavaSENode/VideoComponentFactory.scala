package esir.dom12.nsoc.video.kevgen.JavaSENode
import org.kevoree.framework._
import esir.dom12.nsoc.video._
class VideoComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
override def registerInstance(instanceName : String, nodeName : String)=VideoComponentFactory.registerInstance(instanceName,nodeName)
override def remove(instanceName : String)=VideoComponentFactory.remove(instanceName)
def createInstanceActivator = VideoComponentFactory.createInstanceActivator
}

object VideoComponentFactory extends org.kevoree.framework.osgi.KevoreeInstanceFactory {
def createInstanceActivator: org.kevoree.framework.osgi.KevoreeInstanceActivator = new VideoComponentActivator
def createComponentActor() : KevoreeComponent = {
	new KevoreeComponent(createVideoComponent()){def startComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.video.VideoComponent].startComponent()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.video.VideoComponent].stopComponent()}
override def updateComponent(){getKevoreeComponentType.asInstanceOf[esir.dom12.nsoc.video.VideoComponent].updateComponent()}
}}
def createVideoComponent() : esir.dom12.nsoc.video.VideoComponent ={
val newcomponent = new esir.dom12.nsoc.video.VideoComponent();
newcomponent.getHostedPorts().put("commandVideo",createVideoComponentPORTcommandVideo(newcomponent))
newcomponent.getNeededPorts().put("setshutter",createVideoComponentPORTsetshutter(newcomponent))
newcomponent}
def createVideoComponentPORTcommandVideo(component : VideoComponent) : VideoComponentPORTcommandVideo ={ new VideoComponentPORTcommandVideo(component)}
def createVideoComponentPORTsetshutter(component : VideoComponent) : VideoComponentPORTsetshutter ={ return new VideoComponentPORTsetshutter(component);}
}
