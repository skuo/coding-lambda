package com.coding.lambda;

public class RequestBody {
	private String firstName;
    private String lastName;

    public RequestBody() {
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

	@Override
	public String toString() {
		return "RequestBody [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
