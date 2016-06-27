package cz.fabian.practice.jaxrs.server.provider.convertor;

import cz.fabian.practice.jaxrs.server.model.MyDate;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

/**
 * Created by nfabian on 26.6.16.
 */
@Provider
public class MyDateConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        if(aClass.getName().equals(MyDate.class.getName())) {
            return new ParamConverter<T>() {

                @Override
                public T fromString(String date) {
                    Calendar requestedDate = Calendar.getInstance();
                        if("tomorrow".equals(date)) {
                        requestedDate.add(Calendar.DATE, 1);
                    }
                    else if ("yesterday".equals(date)) {
                        requestedDate.add(Calendar.DATE, -1);
                    }
                    MyDate resultDate = new MyDate();
                    resultDate.setDate(requestedDate.get(Calendar.DATE));
                    resultDate.setMonth(requestedDate.get(Calendar.MONTH));
                    resultDate.setYear(requestedDate.get(Calendar.YEAR));
                    return aClass.cast(resultDate);
                }

                @Override
                public String toString(T t) {
                    if(t == null) {
                        return null;
                    }
                    return t.toString();
                }
            };
        }
        return null;
    }
}
