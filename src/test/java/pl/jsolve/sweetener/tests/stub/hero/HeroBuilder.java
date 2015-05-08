package pl.jsolve.sweetener.tests.stub.hero;

import pl.jsolve.sweetener.builder.Builder;

public class HeroBuilder extends Builder<Hero> {

    public static HeroBuilder aHero() {
        return new HeroBuilder();
    }

    public HeroBuilder withFirstName(String firstName) {
        getBuiltObject().setFirstName(firstName);
        return this;
    }

    public HeroBuilder withLastName(String lastName) {
        getBuiltObject().setLastName(lastName);
        return this;
    }

    public HeroBuilder withNickname(String nickname) {
        getBuiltObject().setNickname(nickname);
        return this;
    }

    public HeroBuilder withId(Long id) {
        getBuiltObject().setId(id);
        return this;
    }
}
