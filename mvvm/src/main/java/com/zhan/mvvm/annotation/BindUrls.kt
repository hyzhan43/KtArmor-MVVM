package com.zhan.mvvm.annotation

/**
 *  @author: HyJame
 *  @date:   2019-11-22
 *  @desc:   TODO
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class BindUrls(vararg val values: String = [])
