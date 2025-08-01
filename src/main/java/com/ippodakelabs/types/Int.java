package com.ippodakelabs.types;

import com.ippodakelabs.types.inter.IDataType;

public class Int implements IDataType {
    static Int dInt = null;
    private Int()
    {

    }
    public static Int get()
    {
        if(dInt == null)
        {
            dInt = new Int();
        }
        return dInt;
    }
    @Override
    public boolean match(Class<?> clazz) {
        return Integer.class.equals(clazz);
    }

    @Override
    public String sqlType() {
        return "INT";
    }
    @Override
    public String stringifyType()
    {
        return "%s";
    }
}

