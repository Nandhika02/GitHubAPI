package org.techArk.tests.CRUD;


import java.util.List;
import java.util.Map;
import org.techArk.utils.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.tekarch.base.APIHelper;
import com.tekarch.base.BaseTest;

//import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class GetRepositoryTests extends BaseTest{

	@Test(description = "Validate get single repository")
    public void testGetSingleRepository() {
        String owner = TestDataUtils.getProperty("repoOwner");
        String repo = TestDataUtils.getProperty("repoName");

        // Log values for debugging
        System.out.println("Owner from properties: " + owner);
        System.out.println("Repo from properties: " + repo);

        Response response = APIHelper.getSingleRepository(owner, repo);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code to be 200 but found " + statusCode);

        if (statusCode == 404) {
            String errorMessage = response.jsonPath().getString("message");
            Assert.fail("Repository not found. Error message: " + errorMessage);
        }

        Assert.assertEquals(response.jsonPath().getString("full_name"), owner + "/" + repo);
        Assert.assertEquals(response.jsonPath().getString("default_branch"), "master");
        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));
    }
	 
	
	@Test(description = "Get a single repository with non-existing repo name")
    public void testGetSingleNonExistentRepository() {
        String owner = TestDataUtils.getProperty("repoOwner");
        String repo = "NonExistentRepo";
        
        
        Response response = APIHelper.getSingleRepository(owner, repo);
        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.jsonPath().getString("message"), "Not Found");
    }
	
	
	 @Test(description = "Validate get all repository")
	    public void testGetAllRepositories() {
		 
		 Response response = APIHelper.getAllRepositories();
	        Assert.assertEquals(response.getStatusCode(), 200);

	        List<Map<String, Object>> repositories = response.jsonPath().getList("");
	        System.out.println("Total Repositories: " + repositories.size());
	    }

	 
	 
}

