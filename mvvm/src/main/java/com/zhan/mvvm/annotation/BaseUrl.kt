package com.zhan.mvvm.annotation

/**
 *  @author: HyJame
 *  @date:   2019-11-22
 *  @desc:   TODO
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class BaseUrl(val value: String) {
}