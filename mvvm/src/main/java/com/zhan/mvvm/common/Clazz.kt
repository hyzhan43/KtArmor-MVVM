package com.zhan.mvvm.common

import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType

/**
 * author：  HyZhan
 * create：  2019/6/24
 * desc：    TODO
 */
object Clazz {

    @Suppress("UNCHECKED_CAST")
    fun <T> getClass(t: Any): Class<T> {
        // 通过反射 获取当前类的父类的泛型 (T) 对应 Class类
        return (t.javaClass.genericSuperclass as ParameterizedType)
                .actualTypeArguments[0]
                as Class<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getFiledClazz(field: Field) = field.genericType as Class<T>
}