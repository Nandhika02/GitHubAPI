package org.techArk.responsePOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;



@Data
@Builder

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepositoryResponse {
	
	public class Root{
	    @JsonProperty(value = "name") 
	    public String name;
	    
	    @JsonProperty(value = "login") 
	    public String login;
	    
	    @JsonProperty(value = "type") 
	    public String type;
	}

}

