package com.westboy.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

import java.util.List;

/**
 * Bean 映射工具类
 *
 * @author pengbo.wang
 * @date 2019/9/9
 * @since 1.0
 */
public abstract class BeanMapper {

    private static final MapperFacade mapper;

    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();
    }

    /**
     * 简单的将 sourceObject 的属性复制到 destinationObject
     */
    public static <S, D> void map(S sourceObject, D destinationObject) {
        mapper.map(sourceObject, destinationObject);
    }

    /**
     * 推荐：极致性能的将 sourceObject 的属性复制到 destinationObject
     */
    public static <S, D> void map(S sourceObject, D destinationObject, Type<S> sourceType, Type<D> destination) {
        mapper.map(sourceObject, destinationObject, sourceType, destination);
    }

    /**
     * 简单的复制出新类型对象
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    /**
     * 极致性能的复制出新类型对象.
     */
    public static <S, D> D map(S source, Type<S> sourceType, Type<D> destinationType) {
        return mapper.map(source, sourceType, destinationType);
    }

    /**
     * 极致性能的复制出新对象列表到 ArrayList
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<S> sourceClass, Class<D> destinationClass) {
        return mapList(sourceList, getType(sourceClass), getType(destinationClass));
    }

    /**
     * 极致性能的复制出新类型对象到 ArrayList
     */
    private static <S, D> List<D> mapList(Iterable<S> sourceList, Type<S> sourceType, Type<D> destinationType) {
        return mapper.mapAsList(sourceList, sourceType, destinationType);
    }

    /**
     * 简单复制出新对象列表到数组
     */
    public static <S, D> D[] mapArray(final D[] destination, final S[] source, final Class<D> destinationClass) {
        return mapper.mapAsArray(destination, source, destinationClass);
    }

    /**
     * 极致性能的复制出新类型对象到数组
     */
    public static <S, D> D[] mapArray(D[] destination, S[] source, Type<S> sourceType, Type<D> destinationType) {
        return mapper.mapAsArray(destination, source, sourceType, destinationType);
    }

    /**
     * 预先获取 orika 转换所需要的 Type，避免每次转换
     */
    private static <E> Type<E> getType(final Class<E> rawType) {
        return TypeFactory.valueOf(rawType);
    }
}
