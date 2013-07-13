package pl.jsolve.sweetener.tests.stub.hero;

import pl.jsolve.sweetener.mapper.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotation.MappableTo;

@MappableTo(Hero.class)
public class HeroSnapshot {

	@MapExactlyTo("id")
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}