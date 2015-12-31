package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.Cookie;
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
    
    public Result agb() {
        return ok(agb.render());
    }
    
    public Result impressum() {
        return ok(impressum.render());
    }
    
    public Result upload() {
    	
    	Cookie name = request().cookies().get("data");
		if (name != null) {

			return ok(upload.render());
		} else {
			return ok(nichtangemeldet.render());
		}
    }
    
    public Result stellenangebot() {
    	return ok(stellenangebot.render());
    }
    
    public Result contact() {
    	return ok(kontakt.render());
    }
    
    public Result profil() {
    	
    	Cookie name = request().cookies().get("data");
		if (name != null) {

			return ok(profil.render());
		} else {
			return ok(nichtangemeldet.render());
		}    			
    }
    
}
