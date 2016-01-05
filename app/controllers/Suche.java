package controllers;

import java.io.Console;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.*;
import play.mvc.BodyParser;                     
import play.libs.Json;
import play.libs.Json.*;
import play.mvc.*;
import play.mvc.Http.Cookie;
import views.html.*;

public class Suche extends Controller{
	public Result suchen(){
		
		String studiengang = request().getQueryString("studiengang"); 
 		String dauerString = request().getQueryString("dauer"); 
 		String bundesland = request().getQueryString("bundesland"); 
 		String was = request().getQueryString("was"); 
 		String wann = request().getQueryString("wann"); 
 		String wo = request().getQueryString("wo"); 

		int dauer = 0;
		try{
			dauer = Integer.parseInt(dauerString);
		}
		catch(NumberFormatException e){
			dauer = 0;
		}
		if(studiengang.equals("undefined")){
			studiengang = "";
		}
		else{
			switch (studiengang) {
			case "inf":
				studiengang = "Informatik";
				break;
			case "bwl":
				studiengang = "BWL";
				break;
			default:
				studiengang = "";
				break;
			}
		}
		if(bundesland.equals("undefined")){
			bundesland = "";
		}
		else{
			switch (bundesland) {
			case "ba_wue":
				bundesland = "Baden-Württemberg";
				break;
			case "bayern":
				bundesland = "Bayern";
				break;
			default:
				bundesland = "";
				break;
			}
		}
		if(was.equals("undefined")){
			was = "";
		}
		if(wo.equals("undefined")){
			wo = "";
		}
		
		ArrayNode result = model.Model.getInstance().getSuche().suchen(bundesland, dauer, studiengang, was, wann, wo);
		
		return ok(result);
	}
}
