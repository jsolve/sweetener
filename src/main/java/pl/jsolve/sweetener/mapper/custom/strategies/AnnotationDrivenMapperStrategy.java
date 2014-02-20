package pl.jsolve.sweetener.mapper.custom.strategies;

import pl.jsolve.sweetener.mapper.annotationDriven.AnnotationDrivenMapper;

public class AnnotationDrivenMapperStrategy implements CustomMapperStrategy {

	@Override
	public Object apply(Object object, Class<?> targetType) {
		if (AnnotationDrivenMapper.isMappableToTargetClass(object, targetType)) {
			return AnnotationDrivenMapper.map(object, targetType);
		}
		return object;
	}
}