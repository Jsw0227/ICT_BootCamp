package controller;

public class ActionFoward {
	private String url; // viewName
	private boolean methods; // 이동방식( false- forward or redirect)

	public void ActionForward() {

	}



	public boolean isMethods() {
		return methods;
	}



	public void setMethods(boolean methods) {
		this.methods = methods;
	}



	public ActionFoward(String url, boolean methods) {
		this.url = url;
		this.methods = methods;
	}



	public ActionFoward(String url) {
		this.url = url;
	}



	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



}
