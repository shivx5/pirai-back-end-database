package projectstack.model;

import lombok.Data;

@Data
public class UserRequest {

	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setUsername(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public UserRequest()
	{
		
	}
}
