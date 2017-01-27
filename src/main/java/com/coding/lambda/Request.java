package com.coding.lambda;

public class Request {
	// path variables
	private String name;
	
	// query variables
	private String qBreed;
	
	// POST JSON payload
	private RequestBody body;
	private String firstName;
    private String lastName;

    public Request() {
    }

    public Request(String firstName, String lastName, String name) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = name;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getqBreed() {
		return qBreed;
	}

	public void setqBreed(String qBreed) {
		this.qBreed = qBreed;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public RequestBody getBody() {
		return body;
	}

	public void setBody(RequestBody body) {
		this.body = body;
	}

	@Override
	public String toString() {
		String bodyStr = null;
		if (body != null)
			bodyStr = body.toString();
		return "Request [name=" + name + ", qBreed=" + qBreed + ", body=" + bodyStr + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

}

