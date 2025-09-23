package com.projects.eShopping.dto;

public class SecurityDetailsResDTO {
	
	private String securityQuestion;
	private String securityAnswer;
	public SecurityDetailsResDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SecurityDetailsResDTO(String securityQuestion, String securityAnswer) {
		super();
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	@Override
	public String toString() {
		return "SecurityDetailsResDTO [securityQuestion=" + securityQuestion + ", securityAnswer=" + securityAnswer
				+ "]";
	}
	
}
