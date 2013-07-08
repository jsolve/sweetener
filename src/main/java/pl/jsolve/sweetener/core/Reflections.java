package pl.jsolve.sweetener.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import pl.jsolve.sweetener.exception.AccessToFieldException;
import pl.jsolve.sweetener.exception.InstanceCreationException;

public class Reflections {

	private final static String DOT = "\\.";
	private final Object object;

	public static Reflections onObject(Object object) {
		return new Reflections(object);
	}

	private Reflections(Object object) {
		this.object = object;
	}

	public static Object getFieldValue(Object o, String stringOfFieldsName) {

		String[] fieldsName = stringOfFieldsName.split(DOT);
		int levelOfNestedObject = 0;
		Class<?> clazz = o.getClass();
		while (!Object.class.equals(clazz)) {

			for (int i = levelOfNestedObject; i < fieldsName.length; i++) {
				Field field = onObject(o).getDeclaredField(fieldsName[i]);
				if (field != null) {
					boolean isLastNestedObject = (i == fieldsName.length - 1);
					if (isLastNestedObject) {
						return onObject(o).getFieldValue(field);
					}
					o = onObject(o).getFieldValue(field);
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
				Field field = onObject(object).getDeclaredField(fieldsName[i]);
				if (field != null) {
					boolean isLastNestedObject = (i == fieldsName.length - 1);
					if (isLastNestedObject) {
						onObject(object).setField(field, value);
						return;
					}
					onObject(object).createValueIfNull(field);
					object = onObject(object).getFieldValue(field);
					levelOfNestedObject++;
				}
			}
			clazz = clazz.getSuperclass();
		}
		throw new AccessToFieldException("The field %s does not exist", fieldsName[levelOfNestedObject]);
	}

	public Reflections createValueIfNull(Field field) {
		try {
			Object valueOfField = getFieldValue(field);
			if (valueOfField == null) {
				Object newInstance = field.getType().newInstance();
				field.setAccessible(true);
				field.set(object, newInstance);
			}
		} catch (Exception ex) {
		} finally {
			field.setAccessible(false);
		}
		return this;
	}

	public Reflections setField(Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(object, value);
		} catch (Exception e) {
			throw new AccessToFieldException("Exception during setting value of %s field", field.getName());
		} finally {
			field.setAccessible(false);
		}
		return this;
	}

	public Object getFieldValue(Field field) {
		try {
			field.setAccessible(true);
			return field.get(object);
		} catch (Exception e) {
			throw new AccessToFieldException("Exception during getting value of %s field", field.getName());
		} finally {
			field.setAccessible(false);
		}
	}

	public Field getDeclaredField(String fieldName) {
		try {
			return object.getClass().getDeclaredField(fieldName);
		} catch (Exception ex) {
			return null;
		}
	}

	public static List<Class<?>> getClassesSatisfyingCondition(Object object, Condition<Class<?>> classesCondition) {
		List<Class<?>> classes = new LinkedList<>();
		Class<?> clazz = object.getClass();
		classes.add(clazz);
		while (!Object.class.equals(clazz)) {
			clazz = clazz.getSuperclass();
			if (classesCondition == null || classesCondition.isSatisfied(clazz)) {
				classes.add(clazz);
			}
		}
		return classes;
	}

	public static List<Class<?>> getClasses(Object object) {
		return getClassesSatisfyingCondition(object, null);
	}

	public static List<Field> getFieldsSatisfyingCondition(Object object, Condition<Field> fieldCondition) {
		List<Field> fields = new LinkedList<>();
		Class<?> clazz = object.getClass();
		while (!Object.class.equals(clazz)) {
			Field[] arrayOfFields = clazz.getDeclaredFields();
			for (Field field : arrayOfFields) {
				if (fieldCondition == null || fieldCondition.isSatisfied(field)) {
					fields.add(field);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return fields;
	}

	public static List<Field> getFields(Object object) {
		return getFieldsSatisfyingCondition(object, null);
	}

	public static List<Field> getFieldsAnnotatedBy(Object object, final Class<? extends Annotation> annotation) {
		return getFieldsSatisfyingCondition(object, new Condition<Field>() {

			@Override
			public boolean isSatisfied(Field field) {
				return field.isAnnotationPresent(annotation);
			}
		});
	}

	public static List<Annotation> getAnnotationsSatisfyingCondtion(Object object, Condition<Annotation> condition) {
		List<Annotation> annotations = new LinkedList<>();
		Class<?> clazz = object.getClass();
		while (!Object.class.equals(clazz)) {
			Annotation[] arrayOfAnnotations = clazz.getDeclaredAnnotations();
			for (Annotation annotation : arrayOfAnnotations) {
				if (condition == null || condition.isSatisfied(annotation)) {
					annotations.add(annotation);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return annotations;
	}

	public static List<Annotation> getAnnotations(Object object) {
		return getAnnotationsSatisfyingCondtion(object, null);
	}

	public static List<Constructor<?>> getConstructorsSatisfyingCondtion(Object object, Condition<Constructor<?>> condition) {
		List<Constructor<?>> constructors = new LinkedList<>();
		Class<?> clazz = object.getClass();
		while (!Object.class.equals(clazz)) {
			Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
			for (Constructor<?> declaredConstructor : declaredConstructors) {
				if (condition == null || condition.isSatisfied(declaredConstructor)) {
					constructors.add(declaredConstructor);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return constructors;
	}

	public static List<Constructor<?>> getConstructors(Object object) {
		return getConstructorsSatisfyingCondtion(object, null);
	}

	public static List<Method> getMethodsSatisfyingCondtion(Object object, Condition<Method> methodsCondition) {
		List<Method> methods = new LinkedList<>();
		Class<?> clazz = object.getClass();
		while (!Object.class.equals(clazz)) {
			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method declaredMethod : declaredMethods) {
				if (methodsCondition == null || methodsCondition.isSatisfied(declaredMethod)) {
					methods.add(declaredMethod);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return methods;
	}

	public static List<Method> getMethods(Object object) {
		return getMethodsSatisfyingCondtion(object, null);
	}

	public static List<Method> getMethodsAnnotatedBy(Object object, final Class<? extends Annotation> annotation) {
		return getMethodsSatisfyingCondtion(object, new Condition<Method>() {

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