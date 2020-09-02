package com.westboy.util;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

/**
 * 对象拷贝工具类
 *
 * @author pengbo
 * @since 2020-08-26
 */
public abstract class BeanCopierUtils {

    private static final Map<String, BeanCopier> beanCopierCacheMap = new HashMap<>();

    private static final Map<String, ConstructorAccess> constructorAccessCache = new ConcurrentHashMap<>();

    /**
     * 将 source 对象的属性拷贝到 target 对象中去
     *
     * @param source source 对象
     * @param target target 对象
     */
    public static void copyProperties(Object source, Object target) {
        String cacheKey = source.getClass().toString() + target.getClass().toString();

        BeanCopier beanCopier = null;

        if (!beanCopierCacheMap.containsKey(cacheKey)) {
            synchronized (BeanCopierUtils.class) {
                if (!beanCopierCacheMap.containsKey(cacheKey)) {
                    beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
                    beanCopierCacheMap.put(cacheKey, beanCopier);
                }
            }
        } else {
            beanCopier = beanCopierCacheMap.get(cacheKey);
        }

        assert beanCopier != null;
        beanCopier.copy(source, target, null);
    }

    public static <T> T copyProperties2(Object source, Class<T> targetClass) {
        String cacheKey = source.getClass().toString() + targetClass.toString();

        BeanCopier beanCopier = null;
        if (!beanCopierCacheMap.containsKey(cacheKey)) {
            synchronized (BeanCopierUtils.class) {
                if (!beanCopierCacheMap.containsKey(cacheKey)) {
                    beanCopier = BeanCopier.create(source.getClass(), targetClass, false);
                    beanCopierCacheMap.put(cacheKey, beanCopier);
                }
            }
        } else {
            beanCopier = beanCopierCacheMap.get(cacheKey);
        }

        assert beanCopier != null;
        T target = null;
        try {
            target = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // target = getConstructorAccess(targetClass).newInstance();

        beanCopier.copy(source, target, null);

        return target;
    }


    private static <T> ConstructorAccess<T> getConstructorAccess(Class<T> targetClass) {
        ConstructorAccess<T> constructorAccess = constructorAccessCache.get(targetClass.toString());
        if(constructorAccess != null) {
            return constructorAccess;
        }
        try {
            constructorAccess = ConstructorAccess.get(targetClass);
            constructorAccess.newInstance();
            constructorAccessCache.put(targetClass.toString(),constructorAccess);
        } catch (Exception e) {
            throw new RuntimeException(format("Create new instance of %s failed: %s", targetClass, e.getMessage()));
        }
        return constructorAccess;
    }

}
