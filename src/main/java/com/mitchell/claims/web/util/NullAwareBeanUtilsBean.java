package com.mitchell.claims.web.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Khaled Ayoubi
 */
public class NullAwareBeanUtilsBean extends BeanUtilsBean {
    @Override
    public void copyProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
        if (value == null) {
            return;
        }
        //TODO this is not generic enough, in real life scenario I'd implement this to distinguish between different type of references and copy them accordingly
        if (!(value instanceof List)) {
            super.copyProperty(bean, name, value);
        }
    }
}
