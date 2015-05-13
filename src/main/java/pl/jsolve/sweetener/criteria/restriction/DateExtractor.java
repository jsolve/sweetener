package pl.jsolve.sweetener.criteria.restriction;

import java.util.Date;

public interface DateExtractor<D> {

    Date extract(D d);
}
