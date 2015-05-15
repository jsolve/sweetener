package pl.jsolve.sweetener.criteria.data;

import java.util.List;
import java.util.Set;

public class ObjectWithCollectionOfNumbers {

    private int index;
    private List<Integer> gradesAsList;
    private Set<Integer> gradesAsSet;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Integer> getGradesAsList() {
        return gradesAsList;
    }

    public void setGradesAsList(List<Integer> gradesAsList) {
        this.gradesAsList = gradesAsList;
    }

    public Set<Integer> getGradesAsSet() {
        return gradesAsSet;
    }

    public void setGradesAsSet(Set<Integer> gradesAsSet) {
        this.gradesAsSet = gradesAsSet;
    }

    @Override
    public String toString() {
        return "ObjectWithCollectionOfNumbers [index=" + index + ", gradesAsList=" + gradesAsList + "]";
    }

    public static class ObjectWithCollectionOfNumbersBuilder {

        private ObjectWithCollectionOfNumbers object;

        private ObjectWithCollectionOfNumbersBuilder(int index) {
            this.object = new ObjectWithCollectionOfNumbers();
            object.setIndex(index);
        }

        public static ObjectWithCollectionOfNumbersBuilder aBuilder(int index) {
            return new ObjectWithCollectionOfNumbersBuilder(index);
        }

        public ObjectWithCollectionOfNumbersBuilder withGradesAsList(List<Integer> grades) {
            object.setGradesAsList(grades);
            return this;
        }

        public ObjectWithCollectionOfNumbersBuilder withGradesAsList(Set<Integer> grades) {
            object.setGradesAsSet(grades);
            return this;
        }

        public ObjectWithCollectionOfNumbers build() {
            return object;
        }
    }
}