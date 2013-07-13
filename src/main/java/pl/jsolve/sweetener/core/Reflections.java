package pl.jsolve.sweetener.core;

import static pl.jsolve.sweetener.core.ConditionFactory.createAlwaysSatisfiedCondition;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pl.jsolve.sweetener.exception.AccessToFieldException;
import pl.jsolve.sweetener.exception.InstanceCreationException;

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

	public static Object getFieldValue(Object o, String stringOfFieldsName) {

		String[] fieldsName = stringOfFieldsName.split(DOT);
		int levelOfNestedObject = 0;
		Class<?> clazz = o.getClass();
		while (!Object.class.equals(clazz)) {

			for (int i = levelOfNestedObject; i < fieldsName.length; i++) {
				Field field = getDeclaredField(o, fieldsName[i]);
				if (field != null) {
					boolean isLastNestedObject = (i == fieldsName.length - 1);
					if (isLastNestedObject) {
						return getFieldValue(o, field);
					}
					o = getFieldValue(o, field);
					levelOfNestedObject++;
					if (o == null) {
						return null;
					}
				}
			}
			clazz = clazz.getSuperclass();
		}
		throw new AccessToFieldException("The field %s does not exist", fieldsName[levelOfNestedObject]);
	}

	public static void setFieldValue(Object object, String stringOfFieldsName, Object value) {

		String[] fieldsName = stringOfFieldsName.split(DOT);
		int levelOfNestedObject = 0;
		Class<?> clazz = object.getClass();
		while (!Object.class.equals(clazz)) {

			for (int i = levelOfNestedObject; i < fieldsName.length; i++) {
				Field field = getDeclaredField(object, fieldsName[i]);
				if (field != null) {
					boolean isLastNestedObject = (i == fieldsName.length - 1);
					if (isLastNestedObject) {
						setField(object, field, value);
						return;
					}
					createValueIfNull(object, field);
					object = getFieldValue(object, field);
					levelOfNestedObject++;
				}
			}
			clazz = clazz.getSuperclass();
		}
		throw new AccessToFieldException("The field %s does not exist", fieldsName[levelOfNestedObject]);
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
		} finally {
			field.setAccessible(false);
		}
	}

	private static void setField(Object object, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(object, value);
		} catch (Exception e) {
			throw new AccessToFieldException("Exception during setting value of %s field", field.getName());
		} finally {
			field.setAccessible(false);
		}
	}

	private static Object getFieldValue(Object object, Field field) {
		try {
			field.setAccessible(true);
			return field.get(object);
		} catch (Exception e) {
			throw new AccessToFieldException("Exception during getting value of %s field", field.getName());
		} finally {
			field.setAccessible(false);
		}
	}

	private static Field getDeclaredField(Object object, String fieldName) {
		try {
			return object.getClass().getDeclaredField(fieldName);
		} catch (Exception ex) {
			return null;
		}
	}

	public static List<Class<?>> getClassesSatisfyingCondition(Object object, Condition<Class<?>> classesCondition) {
		List<Class<?>> classes = new ArrayList<>();
		Class<?> clazz = object.getClass();
		classes.add(clazz);
		while (!Object.class.equals(clazz)) {
			clazz = clazz.getSuperclass();
			if (classesCondition.isSatisfied(clazz)) {
				classes.add(clazz);
			}
		}
		return classes;
	}

	public static List<Class<?>> getClasses(Object object) {
		return getClassesSatisfyingCondition(object, ALWAYS_SATISFIED_CLASS_CONDITION);
	}

	public static List<Field> getFieldsSatisfyingCondition(Object object, Condition<Field> fieldCondition) {
		List<Field> fields = new ArrayList<>();
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
		List<Annotation> annotations = new ArrayList<>();
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
		List<Constructor<?>> constructors = new ArrayList<>();
		Class<?> clazz = object.getClass();
		while (!Object.class.equals(clazz)) {
			Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
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
		List<Method> methods = new ArrayList<>();
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
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new InstanceCreationException("Could not create an instance of class " + clazz, e);
		}
	}
}