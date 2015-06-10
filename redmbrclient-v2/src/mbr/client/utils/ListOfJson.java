/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author maine
 */
public class ListOfJson<T> implements ParameterizedType {

    private Class<?> wrapped;

    public ListOfJson(Class<T> wrapper) {
        this.wrapped = wrapper;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{wrapped};
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
