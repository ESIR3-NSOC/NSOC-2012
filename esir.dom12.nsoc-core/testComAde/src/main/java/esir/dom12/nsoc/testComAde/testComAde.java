package esir.dom12.nsoc.testComAde;

import java.io.IOException;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;


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
    	//if (msg.equalsIgnoreCase("Start")){
    	if (msg!=null){
    		if (msg=="start"){
    			System.out.println("start received!!!");
    		}
    		System.out.println("testComAde:: Received : "+msg);
    		String output=null;
    		output +="\n\nplanningSalleParDate(2013,2,12,41,1)\n";
    		String []planning = getPortByName("comAde", Ade.class).planningSalleParDate(2013,2,12,"41","1");
    		int i=0;
    		while (planning[i]!=null){
    			output+="\n"+planning[i];
    			i++;
    		}
    		output +="\ncoursActuelParEtudiant(Thebault Antoine\n";
    		output+=getPortByName("comAde", Ade.class).coursActuelParEtudiant("Thebault Antoine");
    		output += "\n\nplanningetudiantpardate2013,2,13 (Thebault Antoine)\n";
    		planning = getPortByName("comAde", Ade.class).planningEtudiantParDate(2013,2,13,"Thebault Antoine");
    		i=0;
    		while (planning[i]!=null){
    			output+="\n"+planning[i];
    			i++;
    		}
    		System.out.println("resultat : "+output);
    	}
    }

}
