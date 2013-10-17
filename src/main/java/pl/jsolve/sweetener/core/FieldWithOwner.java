package pl.jsolve.sweetener.core;

import java.lang.reflect.Field;

class FieldWithOwner {

	private final Object owner;
	private final Field field;

	public FieldWithOwner(Field field, Object owner) {
		this.field = field;
		this.owner = owner;
	}

	public Field getField() {
		return field;
	}

	public Object getOwner() {
		return owner;
	}
}