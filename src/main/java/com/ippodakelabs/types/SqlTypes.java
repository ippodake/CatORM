package com.ippodakelabs.types;


import com.ippodakelabs.types.inter.IDataType;

public class SqlTypes {

    public static IDataType get(Class<?> clazz) {
        if(Varchar.get().match(clazz)) return Varchar.get();
        if(Int.get().match(clazz)) return Int.get();
        if(Bool.get().match(clazz)) return Bool.get();
        throw new RuntimeException(clazz.getName() + " is not a compatible data type");
    }

}
