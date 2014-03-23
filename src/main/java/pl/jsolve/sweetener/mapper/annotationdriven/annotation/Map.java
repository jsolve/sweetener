package pl.jsolve.sweetener.mapper.annotationdriven.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Map {

	String fromNested() default "";

	String to() default "";

	Class<?>[] of() default Object.class;

	Class<?> keysAs() default Object.class;

	Class<?> valuesAs() default Object.class;

	Class<?> elementsAs() default Object.class;
}