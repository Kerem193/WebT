package model;

import play.*;
import play.mvc.*;
import play.mvc.Http.Cookie;
import play.libs.Json;
import views.html.*;

import java.net.HttpCookie;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Suche {
	public ArrayNode suchen(String bundesland, int dauer, String studiengang, String was, String wann, String wo){
		
		ResultSet rs;
		Connection con;
		PreparedStatement ps;
		ArrayNode allResults = Json.newArray();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/staj_db", "root", "root");
			
			System.out.println("Datenbankverbindung hergestellt");
			
			List<Object> list = new ArrayList<Object>();
			
			boolean needAnd = false;
			String sql = "SELECT * FROM praktikumsangebot WHERE ";
			if(bundesland != null && !bundesland.isEmpty()){
				sql += "bundesland = ? ";
				needAnd = true;
				list.add(bundesland);
			}
			if(dauer > 0){
				if(needAnd){
					sql += "AND ";
				}
				sql += "dauer = ? ";
				needAnd = true;
				list.add(dauer);
			}
			if(studiengang != null && !studiengang.isEmpty()){
				if(needAnd){
					sql += "AND ";
				}
				sql += "studiengang = ? ";
				needAnd = true;
				list.add(studiengang);
			}
//			TODO: Datum abfragen
//			if(wann != null && !wann.isEmpty()){
//				if(needAnd){
//					sql += "AND ";
//				}
//				sql += "datum = ? ";
//				needAnd = true;
//				list.add(wann);
//			}
			if(was != null && !was.isEmpty()){
				if(needAnd){
					sql += "AND ";
				}
				sql += "(titel LIKE ? OR ";
				sql += "beschreibung LIKE ? )";
				needAnd = true;
				list.add("%" + was + "%");
				list.add("%" + was + "%");
			}
			if(wo != null && !wo.isEmpty()){
				if(needAnd){
					sql += "AND ";
				}
				sql += "stadt = ? ";
				list.add(wo);
			}
			System.out.println("SQL: " + sql);
			ps = con.prepareStatement(sql);
			
			int i = 1;
			for(Object s : list){
				ps.setObject(i, s);
				i++;
			}
			
			rs = ps.executeQuery();

			int index = 0;
			while(rs.next()) {
				ObjectNode result = Json.newObject();
				result.put("id_pra", rs.getInt("id_pra"));
				result.put("titel", rs.getString("titel"));
				result.put("beschreibung", rs.getString("beschreibung"));
				result.put("stadt", rs.getString("stadt"));
				result.put("bundesland", rs.getString("bundesland"));
				result.put("link", rs.getString("link"));
				result.put("studiengang", rs.getString("studiengang"));
				result.put("dauer", rs.getString("dauer"));
				result.put("datum", rs.getString("datum"));
				
				allResults.insert(index, result);
				index++;
			}
			System.out.println("RESULTS: " + allResults);
			return allResults;
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Dieser Fehler ist aufgetreten: " + e.getMessage());
			e.printStackTrace();
			
			return null;
		}
		
		
		
		return null;
	}
}
