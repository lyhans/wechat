package com.gladfish.common.utils;

import java.lang.reflect.Field;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/27 23:45
 */
public class ConvertUtil {

    public static Object convert(Object fromObj,Object toObj){
        Field[] fromFields = fromObj.getClass().getDeclaredFields();
        Field[] toFields = toObj.getClass().getDeclaredFields();
        for(Field field:fromFields){
//            field.getName();
        }
        return null;
    }

}
