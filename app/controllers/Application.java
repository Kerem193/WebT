package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	//HTML Routes
    public Result startseite() {
        return ok(startseite.render());
    }
    
    public Result nichtangemeldet() {
    	return ok(nichtangemeldet.render());
    }
    
    public Result suche() {
        return ok(suche.render());
    }
    
    public Result upload() {
    	System.out.println("Ich war im Controller");
    	return ok(upload.render());
    }
    
    public Result stellenangebot() {
    	return ok(stellenangebot.render());
    }
    
}
