package pl.jsolve.sweetener.tests.stub.hero;

import pl.jsolve.sweetener.comparer.byId.ComparableById;

public class Hero implements ComparableById {
    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;

    public Hero() {
    }

    @Override
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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

    public String getNickname() {
	return nickname;
    }

    public void setNickname(String nickname) {
	this.nickname = nickname;
    }
}
