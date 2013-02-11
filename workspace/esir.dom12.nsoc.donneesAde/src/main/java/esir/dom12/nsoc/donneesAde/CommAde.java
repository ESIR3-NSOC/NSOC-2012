package esir.dom12.nsoc.donneesAde;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;


@Provides({
        @ProvidedPort(name = "consume", type = PortType.MESSAGE)
})
@ComponentType
public class CommAde extends AbstractComponentType {

    @Start
    public void startComponent() {
        System.out.println("Consumer:: Start");
    }

    @Stop
    public void stopComponent() {
        System.out.println("Consumer:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("Consumer:: Update");
    }

    @Port(name = "consume")
    public void consumeHello(Object o) {
        System.out.println("Consumer:: Received " + o.toString());
        if(o instanceof String) {
            String msg = (String)o;
            System.out.println("HelloConsumer received: " + msg);
            if (msg.startsWith("autorisation")){
            	
            }
            else if (msg.startsWith("planningSalleParDate")){
            	
            }
            else if (msg.startsWith("planningEtudiantParDate")){
            	
            }
            else if (msg.startsWith("coursActuelParEtudiant")){
            	
            }
            
        }
    }

}
