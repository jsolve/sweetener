package pl.jsolve.sweetener.mapper.stub;

import pl.jsolve.sweetener.collection.data.Address;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapNested;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;
import pl.jsolve.sweetener.tests.stub.person.StudentSnapshot;

@MappableTo(StudentSnapshot.class)
public class StudentWithBadlyAnnotatedToFieldOnMapNested {

	public static final String NOT_EXISTING_FIELD = "notExistingField";

	@MapNested(fromNested = "city.name", to = NOT_EXISTING_FIELD)
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}