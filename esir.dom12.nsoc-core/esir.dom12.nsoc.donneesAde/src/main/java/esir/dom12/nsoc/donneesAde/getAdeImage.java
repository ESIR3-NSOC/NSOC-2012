package esir.dom12.nsoc.donneesAde;

import java.io.IOException;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

@Provides({
	@ProvidedPort(name = "imageAde", type = PortType.MESSAGE)
})
	
public class getAdeImage extends AbstractComponentType{
	private donneesAde da;
	public getAdeImage (){
		da = new donneesAde();
	}
	@Start
    public void startComponent() {
        System.out.println("getadeimage:: Start");
        
    }

    @Stop
    public void stopComponent() {
        System.out.println("getadeimage:: Stop");
    }

    @Update
    public void updateComponent() {
        System.out.println("getadeimage:: Update");
    }
	@Port(name="imageAde")
    public void getAdeUrl (Object o) throws IOException{
    	String msg=null;
    	if(o instanceof String) {
            msg = (String)o;
            System.out.println("HelloConsumer received: " + msg);
        }
    	String idTree = da.recupererIdEtudiant(msg);
    	String url = "https://plannings.univ-rennes1.fr/ade/imageEt?identifier=5649664c3540f4b8885b484a11514238&projectId=31&idPianoWeek=25&idPianoDay=0%2C1%2C2%2C3%2C4&idTree="+idTree+"%2C3237&width=1087&height=464&lunchName=REPAS&displayMode=1057855&showLoad=false&ttl=1360855171072&displayConfId=53";
    	getPortByName("imageAde", MessagePort.class).process(url);
    }
}
