package pl.jsolve.sweetener.tests.stub.person;

import pl.jsolve.sweetener.mapper.annotation.MapExactlyTo;

public class Address {

	@MapExactlyTo("address")
	private String street;
	private String city;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
