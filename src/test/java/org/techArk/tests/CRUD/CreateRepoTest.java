package org.techArk.tests.CRUD;

import org.techArk.requestPOJO.CreateRepoRequest;
import org.techArk.utils.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tekarch.base.APIHelper;
import com.tekarch.base.BaseTest;

import io.restassured.response.Response;

public class CreateRepoTest extends BaseTest {
	
	@Test(description = "Create a new repository")
    public void testCreateRepository() {
        CreateRepoRequest createRepoRequest = new CreateRepoRequest("Hello-world", "This is your first repo!", "https://github.com", false);
        Response response = APIHelper.createRepository(createRepoRequest);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), createRepoRequest.getName());
        Assert.assertEquals(response.jsonPath().getString("owner.login"), TestDataUtils.getProperty("username"));
        Assert.assertEquals(response.jsonPath().getString("owner.type"), "User");
    }

	 @Test(description = "Negative test: create repository with an existing name")
	    public void testCreateRepositoryWithExistingName() {
	        CreateRepoRequest createRepoRequest = new CreateRepoRequest("Hello-World", "Duplicate repo name", "https://github.com", false);
	        Response response = APIHelper.createRepository(createRepoRequest);
	        Assert.assertEquals(response.getStatusCode(), 422);
	        Assert.assertEquals(response.jsonPath().getString("message"), "name already exists on this account");
	    }

	
}
