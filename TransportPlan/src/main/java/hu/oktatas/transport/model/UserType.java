package hu.oktatas.transport.model;

import java.util.Set;

public class UserType {
	String username;
	String password;
	Set<String> role;

	public UserType(String username, String password, Set<String> role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public UserType() {
		// TODO Auto-generated constructor stub
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

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

}
