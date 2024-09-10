package com.example.demo.dto;

public class RegisterFormDTO {
	private Integer userId;

	private String uname;

	private String email;

	private String pwd;

	private String pwdUpdated;

	private Long phno;

	private Integer countryId;

	private Integer stateId;

	private Integer cityId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdUpdated() {
		return pwdUpdated;
	}

	public void setPwdUpdated(String pwdUpdated) {
		this.pwdUpdated = pwdUpdated;
	}

	public Long getPhno() {
		return phno;
	}

	public void setPhno(Long phno) {
		this.phno = phno;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "RegisterFormDTO [userId=" + userId + ", uname=" + uname + ", email=" + email + ", pwd=" + pwd
				+ ", pwdUpdated=" + pwdUpdated + ", phno=" + phno + ", countryId=" + countryId + ", stateId=" + stateId
				+ ", cityId=" + cityId + ", getUserId()=" + getUserId() + ", getUname()=" + getUname() + ", getEmail()="
				+ getEmail() + ", getPwd()=" + getPwd() + ", getPwdUpdated()=" + getPwdUpdated() + ", getPhno()="
				+ getPhno() + ", getCountryId()=" + getCountryId() + ", getStateId()=" + getStateId() + ", getCityId()="
				+ getCityId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	

}
