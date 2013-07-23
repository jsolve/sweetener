package pl.jsolve.sweetener.mapper.annotationDriven.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MapExactlyTo {

	String value();

	Class<?> of() default Object.class;
}
