

import org.testng.Assert;

import org.testng.annotations.Test;


import io.restassured.response.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class NewTest1 {
	Response response = null;
	JSONParser parser = new JSONParser();
	JSONObject obj = null;
	JSONObject obj1 = null;
	String id, id1;
	
  @Test(priority = 2)
  public void f() {
	  response = given().get("http://10.0.1.86/snl/rest/v1/board.json");
	  Assert.assertEquals(response.statusCode(), 200);
  }
  @Test(priority = 1)
  public void create(){
	  response = given().get("http://10.0.1.86/snl/rest/v1/board/new.json");
	  Assert.assertEquals(response.statusCode(), 200);
  }
  @Test(priority = 5)
  public void details(){
	  response = given().get("http://10.0.1.86/snl/rest/v1/board/4000.json");  
	  Assert.assertEquals(response.statusCode(), 200);
	  given().put("http://10.0.1.86/snl/rest/v1/board/4000.json"); 
	  given().delete("http://10.0.1.86/snl/rest/v1/board/3653.json");
	  
  }
  @Test(priority = 3)
 public void createNewPlayer() throws FileNotFoundException, IOException, ParseException{
		
				obj =  (JSONObject) parser.parse(new FileReader("C:\\Users\\arpitbajpai\\Downloads\\workspace\\assignment-snl-services\\db.json"));
		
		
	response =	given().contentType("application/json").body(obj).when().post("http://10.0.1.86/snl/rest/v1/player.json");
	Assert.assertEquals(response.statusCode(), 200);
	System.out.println(response.asString());
	 //id1=(response.getBody().jsonPath().getJsonObject("response.player.id")).toString();
	 obj1 =  (JSONObject) parser.parse(new FileReader("C:\\Users\\arpitbajpai\\Downloads\\workspace\\assignment-snl-services\\db2.json"));
	 response =	given().contentType("application/json").body(obj1).when().put("http://10.0.1.86/snl/rest/v1/player/519.json");	 
		Assert.assertEquals(response.statusCode(), 200);

	 System.out.println(response.asString());
	// response =	given().delete("http://10.0.1.86/snl/rest/v1/player/989.json");	 
	//	Assert.assertEquals(response.statusCode(), 200);

	 //System.out.println(response.asString());

  }
  @Test(priority = 4)
  public void movePlayer(){
	  response = given().get("http://10.0.1.86/snl/rest/v1/move/4000.json?player_id=519");
	  Assert.assertEquals(response.statusCode(), 200);
  }
  }
  
