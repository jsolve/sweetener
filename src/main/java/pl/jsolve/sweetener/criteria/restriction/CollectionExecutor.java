package pl.jsolve.sweetener.criteria.restriction;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import pl.jsolve.sweetener.exception.AccessToFieldException;

class CollectionExecutor {

    public boolean perform(Object elements, Executor executor) {

        if (!(elements instanceof Collection) && !(elements.getClass().isArray())) {
            throw new AccessToFieldException("Type mismatch. Expected Collection, Map or Array but was "
                    + elements.getClass().getCanonicalName());
        }
        // check whether elements are collection
        if (elements instanceof Collection) {
            return executor.execute((Collection) elements);
        } else
        // check whether elements are map
        if (elements instanceof Map) {
            return executor.execute((Map) elements);
        } else
        // check whether elements are array
        if (elements.getClass().isArray()) {
            Object[] fieldValueAsArray = null;
            try {
                // check whether elements are array of objects
                fieldValueAsArray = (Object[]) elements;
            } catch (ClassCastException ex) {
                // exception is thrown for array of primitives. Rewrite primitives to objects
                int length = Array.getLength(elements);
                fieldValueAsArray = new Object[length];
                for (int i = 0; i < Array.getLength(elements); ++i) {
                    fieldValueAsArray[i] = Array.get(elements, i);
                }
            }
            return executor.execute(fieldValueAsArray);
        }

        return true;
    }

    public interface Executor {
        public boolean execute(Collection elements);

        public boolean execute(Map elements);

        public boolean execute(Object[] elements);
    }
}
