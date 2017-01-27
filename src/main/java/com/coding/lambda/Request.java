package com.coding.lambda;

public class Request {
	// path variables
	private String name;
	
	// POST JSON payload
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

}

