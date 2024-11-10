package org.techArk.requestPOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateRepoRequest {

 @JsonProperty(value = "name")
	public String name;
 
 @JsonProperty(value = "description")
	private String description;
 
 @JsonProperty(value = "homepage")
	private String homepage;
	    
 @JsonProperty("private")
	private boolean isPrivate;
	}

