package esir.dom12.nsoc.donneesAde;

import java.io.IOException;
import java.net.MalformedURLException;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class donneesArbre {
	static String category="category=", branchId="branchId=";
	static String etudiants=category+"trainee", enseignants=category+"instructor", salles=category+"room";//category
	static String[] esir = {branchId+"5238",branchId+"5833",branchId+"346"};//branchId 5833?? 346??
	static String esir1=branchId+"997", esir2=branchId+"111", esir3=branchId+"982";//branchId
	
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, ScriptException {
		recupererArbre(etudiants);//+"&"+esir[0]);
	}
	

	static void recupererArbre(String requete) throws FailingHttpStatusCodeException, MalformedURLException, IOException, ScriptException{

		// Create a method instance.
		String url="http://plannings.univ-rennes1.fr/ade/standard/gui/tree.jsp";
		//String url="http://plannings.univ-rennes1.fr/ade/standard/gui/interface.jsp";
		//String url = "http://plannings.univ-rennes1.fr/ade/standard/gui/tree.jsp?calType=ical&login=ical&password=visu&projectId=31&"+requete;
		//String url="http://plannings.univ-rennes1.fr/ade/custom/modules/plannings/direct_cal.jsp?calType=ical&login=cal&password=visu&resources=349&firstDate=2012-12-19&lastDate=2012-12-19&projectId=31";
		System.out.println("url : "+url+"\n-----------------\n");
    
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
		WebWindow ww = null;
	    HtmlPage page = webClient.getPage(url);
	    
	    // Get the form that we are dealing with and within that form, 
	    // find the submit button and the field that we want to change.
	    final HtmlForm form = page.getFormByName("form");
	    final HtmlSubmitInput button = form.getInputByName("submit");
	    final HtmlTextInput usernameTextField = form.getInputByName("username");
	    final HtmlPasswordInput passwordTextField = form.getInputByName("password");
	    // Change the value of the text field
	    usernameTextField.setValueAttribute("29003625");
	    passwordTextField.setValueAttribute("kangblo!");
	    // Now submit the form by clicking the button and get back the second page.
	    HtmlPage page2 = button.click();	    
	   String rep = page2.getWebResponse().getContentAsString();
	    System.out.println(rep+"-------------------------59--------------------------------------------------------------------------------\n\n\n");

	    final HtmlImageInput ip = page2.getFirstByXPath("//input");//[@nsrc='/ade/button?text=Ok&red=false&cssClass=okbutton']
	    HtmlPage page3 = (HtmlPage) ip.click();
	    rep = page3.getWebResponse().getContentAsString();
	    System.out.println(rep+"-------------------------------66--------------------------------------------------------------------------\n\n\n");
	    ScriptResult sr = page3.executeJavaScript("checkCategory('trainee')");
	    System.out.println("script result... :  "+sr.toString());
	    webClient.closeAllWindows();
	}
}
