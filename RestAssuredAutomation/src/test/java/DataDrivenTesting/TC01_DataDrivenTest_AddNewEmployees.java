package DataDrivenTesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC01_DataDrivenTest_AddNewEmployees {
	
	@Test(dataProvider = "employeeDataProvider")
	void postNewEmployees(String employeeName, String employeeSalary, String employeeAge) {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		//JSONObject as request as this is POST Request
		//Here we created data which we send along with POST Request
		JSONObject requestParams = new JSONObject();
		//Body Parameters
		requestParams.put("name", employeeName);
		requestParams.put("salary", employeeSalary);
		requestParams.put("age", employeeAge);
		//Request data is in JSON format
		//Adda a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");
		
		//Add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		//POST Request
		Response response = httpRequest.request(Method.POST,"/create");
		
		//Capture Resposne Body to perform validations
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		Assert.assertEquals(responseBody.contains(employeeName),true);
		Assert.assertEquals(responseBody.contains(employeeSalary),true);
		Assert.assertEquals(responseBody.contains(employeeAge),true);
		
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is:" +statusCode);
		Assert.assertEquals(statusCode, 200);
				
	}

	//Using Hard Coded Values
//	@DataProvider(name = "employeeDataProvider")
//	String[][] getEmployeeData(){
//		
//		String employeeData[][] = {{"abc12","3000","40"}, {"def23","30000","41"}, {"ghi34","4000","41"}};
//		return(employeeData);
//		
//	}

	//Using Excel
	@DataProvider(name = "employeeDataProvider")
	String[][] getEmployeeData() throws IOException{
		
		String path = System.getProperty("user.dir")+"/src/test/java/datadriventesting/employeeData.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "EmployeesData");
		int colcount = XLUtils.getCellCount(path, "EmployeesData", rownum);
		
		String employeeData[][] = new String[rownum][colcount];
		
		for(int i=1; i<=rownum; i++)
		{
			for(int j=0; j<colcount; j++) {
				employeeData[i-1][j] = XLUtils.getCellData(path, "EmployeesData", i, j);
				
			}
		}
		
		return(employeeData);
		
	}
	

}
