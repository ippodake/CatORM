package com.ippodakelabs.types.inter;

public interface IDataType {
    boolean match(Class<?> clazz);
    String sqlType();
    String stringifyType();
}
