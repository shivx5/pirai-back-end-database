package projectstack.model;

import lombok.Data;

@Data
public class UserResponse {

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String token;
	public UserResponse(String token, String message) {
		super();
		this.token = token;
		this.message = message;
	}
	private String message;
	
	public UserResponse()
	{
		
	}
}
