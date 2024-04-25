package com.java.springboot.Bean;

public class EmployeeBean {

	private long idEmployee;
	private String nomEmployee;
	private String prenomEmployee;
	private Boolean gender;
	private String sGender;
	private int communeId;
	private String nomCommune;

	public long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(long idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getNomEmployee() {
		return nomEmployee;
	}

	public void setNomEmployee(String nomEmployee) {
		this.nomEmployee = nomEmployee;
	}

	public String getPrenomEmployee() {
		return prenomEmployee;
	}

	public void setPrenomEmployee(String prenomEmployee) {
		this.prenomEmployee = prenomEmployee;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getsGender() {
		return sGender;
	}

	public void setsGender(String sGender) {
		this.sGender = sGender;
	}

	public int getCommuneId() {
		return communeId;
	}

	public void setCommuneId(int communeId) {
		this.communeId = communeId;
	}

	public String getNomCommune() {
		return nomCommune;
	}

	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

}
