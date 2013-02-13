package esir.dom12.nsoc.testComAde;

import java.io.IOException;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;


@Requires({
        @RequiredPort(name = "comAde", type = PortType.SERVICE,className=Ade.class, optional = true)
})
@Provides({
    @ProvidedPort(name = "output", type = PortType.MESSAGE)
})

@ComponentType
public class testComAde extends AbstractComponentType{
	
	@Start
    public void startComponent() throws IOException {
        System.out.println("TestComAde:: Start");
	}

    @Stop
    public void stopComponent() {
        System.out.println("testComAde:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("testComAde:: Update");
    }

    @Port(name = "output")
    public void test(Object o) throws IOException {
    	String msg=null;
    	if(o instanceof String) {
    		msg = (String)o;
        }
    	if (msg!=null){
    		System.out.println("testComAde:: Received : "+msg);
    		String output;
    		String []planning = getPortByName("comAde", Ade.class).planningSalleParDate(2013,2,12,"41","1");
    		output=planning[0]+"\n"+planning[1];
    		output+=getPortByName("comAde", Ade.class).coursActuelParEtudiant("Thebault Antoine");
    		planning = getPortByName("comAde", Ade.class).planningEtudiantParDate(2013,2,13,"Thebault Antoine");
    		output="    "+planning[0]+planning[1]+planning[2];
    		System.out.println("resultat : "+output);
    		MessagePort portKnx = getPortByName("output", MessagePort.class);
    		if(portKnx != null){
    			portKnx.process(output); // Ecrit les données sur le port
    		}
    	}
    }

}
