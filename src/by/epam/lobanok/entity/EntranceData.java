package by.epam.lobanok.entity;

import java.io.Serializable;

public class EntranceData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;
	
	public EntranceData() {}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntranceData other = (EntranceData) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EntranceData [login=" + login + ", password=" + password + "]";
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	public static class Builder {
		private EntranceData entranceData;
		
		public Builder() {
			entranceData = new EntranceData();
		}
		
		public Builder withLogin(String login) {
			entranceData.login = login;
			return this;			
		}
		
		public Builder withPassword(String password) {
			entranceData.password = password;
			return this;			
		}
		
		public EntranceData build() {
			return entranceData;
		}		
	}
}
