package projectstack.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


import lombok.Data;

@Data
@Entity
@Table(name="usrtab")
public class User {

	@Id
	@GeneratedValue()
	private Integer id;
//	private String name;
	
	@Column(unique=true)
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	private String mobilenumber;
	private String username;
	private String password;
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(
			name="rolestab",
			joinColumns=@JoinColumn(name="id"))
	@Column(name="role")
	private Set<String>roles;
	public Integer getId() {
		return id;
	}
	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	private String Address;
	private String City;
	private String State;
	private String Country;
	private String FirstName;
	private String LastName;

	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	} 
	public User(Integer id, String email, String mobilenumber, String username, String password, Set<String> roles,
		String address, String city, String state, String country, String firstName, String lastName) {
	super();
	this.id = id;
	this.email = email;
	this.mobilenumber = mobilenumber;
	this.username = username;
	this.password = password;
	this.roles = roles;
	this.Address = address;
	this.City = city;
	this.State = state;
	this.Country = country;
	this.FirstName = firstName;
	this.LastName = lastName;
   }
	public User()
	{
		
	}
	
	
}
