package com.ippodakelabs;



import com.ippodakelabs.anotations.Database;
import com.ippodakelabs.anotations.Entity;
import com.ippodakelabs.anotations.TableActions;
import com.ippodakelabs.proxy.handlers.AppDatabaseHandler;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class IppodakeORM {
    public static Connection connection = null;

    static void setConnection(Connection connection) {
        IppodakeORM.connection = connection;
    }

    public static Connection getConnection() {
        if(connection == null) throw new RuntimeException("please set the connection to the database");
        return connection;
    }

    public static <T> T buildDataBase(Class<T> appDatabase, Connection connection,boolean dropPrevTables)
    {
        setConnection(connection);
        var annotation = appDatabase.getAnnotation(Database.class);
        Class<?>[] entities = annotation.entities();
        for (var entity: entities)
        {
            if(!entity.isAnnotationPresent(Entity.class)) throw new RuntimeException(entity.getName()+ " must have a Entity annotation");
            try
            {
                var st = getConnection().createStatement();
                if(dropPrevTables) TableActions.dropTable(connection, entity.getAnnotation(Entity.class).value());
                if(!TableActions.doesTableExist(connection,entity.getAnnotation(Entity.class).value())) st.executeUpdate(TableActions.getSql(entity));


            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return (T) Proxy.newProxyInstance(appDatabase.getClassLoader(), new Class[]{appDatabase}, new AppDatabaseHandler());


    }

}
