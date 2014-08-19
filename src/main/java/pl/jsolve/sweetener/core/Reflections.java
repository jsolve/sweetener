package pl.jsolve.sweetener.core;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.exception.*;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

import static pl.jsolve.sweetener.core.ConditionFactory.*;

public final class Reflections {

	private static final String DOT = "\\.";
	private static final Condition<Class<?>> ALWAYS_SATISFIED_CLASS_CONDITION = createAlwaysSatisfiedCondition();
	private static final Condition<Field> ALWAYS_SATISFIED_FIELD_CONDITION = createAlwaysSatisfiedCondition();
	private static final Condition<Annotation> ALWAYS_SATISFIED_ANNOTATION_CONDITION = createAlwaysSatisfiedCondition();
	private static final Condition<Constructor<?>> ALWAYS_SATISFIED_CONSTRUCTOR_CONDITION = createAlwaysSatisfiedCondition();
	private static final Condition<Method> ALWAYS_SATISFIED_METHOD_CONDITION = createAlwaysSatisfiedCondition();

	private Reflections() {
		throw new AssertionError("Using constructor of this class is prohibited.");
	}

	public static Class<?> getFieldType(Object object, String stringOfFieldsName) {
		FieldWithOwner field = getLastNestedField(object, stringOfFieldsName);
		throwExceptonWhenFieldIsNotPresent(field, stringOfFieldsName);
		return field.getField().getType();
	}

	public static Object getFieldValue(Object object, String stringOfFieldsName) {
		FieldWithOwner field = getLastNestedField(object, stringOfFieldsName);
		throwExceptonWhenFieldIsNotPresent(field, stringOfFieldsName);
		return getFieldValue(field.getOwner(), field.getField());
	}

	public static void setFieldValue(Object object, String stringOfFieldsName, Object value) {
		FieldWithOwner field = getLastNestedField(object, stringOfFieldsName);
		throwExceptonWhenFieldIsNotPresent(field, stringOfFieldsName);
		setField(field.getOwner(), field.getField(), value);
	}

	private static void throwExceptonWhenFieldIsNotPresent(FieldWithOwner field, String stringOfFieldsName) {
		if (field == null) {
			throw new AccessToFieldException("The field %s does not exist", stringOfFieldsName);
		}
	}

	public static boolean isFieldPresent(Object object, String stringOfFieldsName) {
		return getLastNestedField(object, stringOfFieldsName) != null;
	}

	private static FieldWithOwner getLastNestedField(Object object, String stringOfFieldsName) {
		String[] fieldsName = stringOfFieldsName.split(DOT);
		int levelOfNestedObject = 0;
		Class<?> clazz = object.getClass();
		while (!Object.class.equals(clazz)) {
			Class<?> nestedClass = clazz;
			for (int i = levelOfNestedObject; i < fieldsName.length; i++) {
				Field field = getDeclaredField(nestedClass, fieldsName[i]);
				if (field != null) {
					boolean isLastNestedObject = (i == fieldsName.length - 1);
					if (isLastNestedObject) {
						return new FieldWithOwner(field, object);
					}
					createValueIfNull(object, field);
					object = getFieldValue(object, field);
					nestedClass = object.getClass();
					levelOfNestedObject++;
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}

	private static void createValueIfNull(Object object, Field field) {
		try {
			Object valueOfField = getFieldValue(object, field);
			if (valueOfField == null) {
				Object newInstance = field.getType().newInstance();
				field.setAccessible(true);
				field.set(object, newInstance);
			}
		} catch (Exception ex) {
			throw new InstanceCreationException(
					"Could not create new instance of " + field.getType(), ex);
		} finally {
			field.setAccessible(false);
		}
	}

	private static void setField(Object object, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(object, value);
		} catch (Exception e) {
			throw new AccessToFieldException(
					"Exception during setting value of %s field\n%s",
					field.getName(), e.getMessage());
		} finally {
			field.setAccessible(false);
		}
	}

	private static Object getFieldValue(Object object, Field field) {
		try {
			field.setAccessible(true);
			return field.get(object);
		} catch (Exception e) {
			throw new AccessToFieldException(
					"Exception during getting value of %s field",
					field.getName());
		} finally {
			field.setAccessible(false);
		}
	}

	private static Field getDeclaredField(Class<?> clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (Exception ex) {
			return null;
		}
	}

	public static List<Class<?>> getClassesSatisfyingCondition(Class<?> clazz, Condition<Class<?>> classesCondition) {
		List<Class<?>> classes = Collections.newLinkedList();
		classes.add(clazz);
		while (!Object.class.equals(clazz) && !clazz.isInterface()
				&& !clazz.isPrimitive()) {
			clazz = clazz.getSuperclass();
			if (classesCondition.isSatisfied(clazz)) {
				classes.add(clazz);
			}
		}
		return classes;
	}

	public static List<Class<?>> getClassesSatisfyingCondition(Object object, Condition<Class<?>> classesCondition) {
		return getClassesSatisfyingCondition(object.getClass(), classesCondition);
	}

	public static List<Class<?>> getClasses(Class<?> clazz) {
		return getClassesSatisfyingCondition(clazz, ALWAYS_SATISFIED_CLASS_CONDITION);
	}

	public static List<Class<?>> getClasses(Object object) {
		return getClassesSatisfyingCondition(object, ALWAYS_SATISFIED_CLASS_CONDITION);
	}

	public static List<Field> getFieldsSatisfyingCondition(Object object, Condition<Field> fieldCondition) {
		List<Field> fields = Collections.newLinkedList();
		Class<?> clazz = object.getClass();
		while (!Object.class.equals(clazz)) {
			Field[] arrayOfFields = clazz.getDeclaredFields();
			for (Field field : arrayOfFields) {
				if (fieldCondition.isSatisfied(field)) {
					fields.add(field);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return fields;
	}

	public static List<Field> getFields(Object object) {
		return getFieldsSatisfyingCondition(object, ALWAYS_SATISFIED_FIELD_CONDITION);
	}

	public static List<Field> getFieldsAnnotatedBy(Object object, final Class<? extends Annotation> annotation) {
		return getFieldsSatisfyingCondition(object, new Condition<Field>() {

			@Override
			public boolean isSatisfied(Field field) {
				return field.isAnnotationPresent(annotation);
			}
		});
	}

	public static List<Annotation> getAnnotationsSatisfyingCondition(Object object, Condition<Annotation> condition) {
		List<Annotation> annotations = Collections.newArrayList();
		Class<?> clazz = object.getClass();
		while (!Object.class.equals(clazz)) {
			Annotation[] arrayOfAnnotations = clazz.getDeclaredAnnotations();
			for (Annotation annotation : arrayOfAnnotations) {
				if (condition.isSatisfied(annotation)) {
					annotations.add(annotation);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return annotations;
	}

	public static List<Annotation> getAnnotations(Object object) {
		return getAnnotationsSatisfyingCondition(object, ALWAYS_SATISFIED_ANNOTATION_CONDITION);
	}

	public static List<Constructor<?>> getConstructorsSatisfyingCondition(Object object, Condition<Constructor<?>> condition) {
		List<Constructor<?>> constructors = Collections.newArrayList();
		Class<?> clazz = object.getClass();
		while (!Object.class.equals(clazz)) {
			Constructor<?>[] declaredConstructors = clazz
					.getDeclaredConstructors();
			for (Constructor<?> declaredConstructor : declaredConstructors) {
				if (condition.isSatisfied(declaredConstructor)) {
					constructors.add(declaredConstructor);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return constructors;
	}

	public static List<Constructor<?>> getConstructors(Object object) {
		return getConstructorsSatisfyingCondition(object, ALWAYS_SATISFIED_CONSTRUCTOR_CONDITION);
	}

	public static List<Method> getMethodsSatisfyingCondition(Object object, Condition<Method> methodsCondition) {
		List<Method> methods = Collections.newArrayList();
		Class<?> clazz = object.getClass();
		while (!Object.class.equals(clazz)) {
			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method declaredMethod : declaredMethods) {
				if (methodsCondition.isSatisfied(declaredMethod)) {
					methods.add(declaredMethod);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return methods;
	}

	public static List<Method> getMethods(Object object) {
		return getMethodsSatisfyingCondition(object, ALWAYS_SATISFIED_METHOD_CONDITION);
	}

	public static List<Method> getMethodsAnnotatedBy(Object object, final Class<? extends Annotation> annotation) {
		return getMethodsSatisfyingCondition(object, new Condition<Method>() {

			@Override
			public boolean isSatisfied(Method declaredMethod) {
				return declaredMethod.isAnnotationPresent(annotation);
			}
		});
	}

	public static <T> T tryToCreateNewInstance(Class<T> clazz) {
		try {
			return tryToCreateNewInstance(clazz.getDeclaredConstructor());
		} catch (ReflectiveOperationException e) {
			throw new InstanceCreationException("Could not create an instance of class " + clazz, e);
		}
	}

	public static <T> T tryToCreateNewInstance(Constructor constructor, Object... params) {
		try {
			constructor.setAccessible(true);
			return (T) constructor.newInstance(params);
		} catch (ReflectiveOperationException e) {
			throw new InstanceCreationException("Could not create an instance of class " + constructor.getDeclaringClass(), e);
		}
	}
}
