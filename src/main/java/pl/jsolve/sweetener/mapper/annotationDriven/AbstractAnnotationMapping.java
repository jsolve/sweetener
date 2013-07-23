package pl.jsolve.sweetener.mapper.annotationDriven;

import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.exception.AccessToFieldException;
import pl.jsolve.sweetener.mapper.annotationDriven.exception.MappingException;

abstract class AbstractAnnotationMapping {

	abstract <S, T> void apply(S sourceObject, T targetObject);

	protected void throwExceptionWhenFieldIsNotPresent(Object object, String fieldName) {
		try {
			Reflections.getFieldValue(object, fieldName);
		} catch (AccessToFieldException e) {
			throw new MappingException("%s does not contain field %s", object.getClass(), fieldName);
		}
	}
}