package org.bahmni.gauge.data;

import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.bahmni.gauge.common.AppGlobal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StoreHelper {
    public static <T> boolean store(Class<T> tClass, T entity) {
       //
        List<T> entities = (List<T>) DataStoreFactory.getScenarioDataStore().get(tClass.getSimpleName());
        //List<T> entities = (List<T>) ScenarioDataStore.get(tClass.getSimpleName());
        if (null == entities) {
            entities = new LinkedList<>();
          //  ScenarioDataStore.put(tClass.getSimpleName(), entities);
            DataStoreFactory.getScenarioDataStore().put(tClass.getSimpleName(), entities);
        }
        return entities.add(entity);
    }

    public static <T> T getLatest(Class<T> tClass) {
      List<T> entities = (List<T>) DataStoreFactory.getScenarioDataStore().get(tClass.getSimpleName());
     //   List<T> entities = (List<T>) ScenarioDataStore.get(tClass.getSimpleName());
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }

        return entities.get(entities.size() - 1);
    }

    public static <T> String getVariableInClass(String className, String var) {
       List<T> entities = (List<T>) DataStoreFactory.getScenarioDataStore().get(className);
        //List<T> entities = (List<T>) ScenarioDataStore.get(className);
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }
        try {
            return BeanUtils.getProperty(entities.get(entities.size() - 1),var);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getLatest(String tClassName) {
        List<T> entities = (List<T>) DataStoreFactory.getScenarioDataStore().get(tClassName);
     //   List<T> entities = (List<T>) ScenarioDataStore.get(tClassName);
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }
        return entities.get(entities.size() - 1);
    }

    public static <T> List<T> getAll(Class<T> tClass) {
        List<T> entities = (List<T>) DataStoreFactory.getScenarioDataStore().get(tClass.getSimpleName());
       // List<T> entities = (List<T>) ScenarioDataStore.get(tClass.getSimpleName());
        if (null == entities) {
            return Collections.EMPTY_LIST;
        }
        return entities;
    }
    public static AppGlobal getAppGlobal(){
            AppGlobal appGlobal=getLatest(AppGlobal.class);
            if (appGlobal==null) {
                appGlobal = new AppGlobal();
            }
            store(AppGlobal.class,appGlobal);
            return appGlobal;
    }
}
