import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC06_GET_ExtractValuesofEachNodeInJSON {
	
	@Test
	public void ExtractValuesofEachNodeInJSON() {
	
	//Specify Base URI
	RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
	
	//Request Object - For Sending Request to the server
	RequestSpecification httpRequest = RestAssured.given();
	
	//Response Object (response) - To store the Response
	//response object contains response body, header, status code etc
	Response response = httpRequest.request(Method.GET,"/Delhi");
	
	//Json path - to extract values of Nodes
	JsonPath jsonPath = response.jsonPath();
	System.out.println(jsonPath.get("City"));
	System.out.println(jsonPath.get("Temperature"));
	System.out.println(jsonPath.get("Humidity"));
	System.out.println(jsonPath.get("WeatherDescription"));
	System.out.println(jsonPath.get("WindSpeed"));
	System.out.println(jsonPath.get("WindDirectionDegree"));
	Assert.assertEquals(jsonPath.get("City"), "Delhi");
	
	}
	
}
