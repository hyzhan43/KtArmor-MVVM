# 前言

学习了Kotlin有一段时间了, 每次写项目/Demo的时候, 总是用到`网络请求`、`MVP`、`MVVM`、`常用工具类`、`通用自定义View`, 索性把这些整合到一起, 搭成一个Android的脚手架——KtArmor. 框架是我个人经验的积累, 总结. 如有不妥, 望各位大佬指出.

# 什么是KtArmor ?

`KtArmor` 寓意着 为Android 赋予`战斗装甲`, 方便开发者快速进行Android 开发。节约开发者开发时间。为了满足开发者需求, 我整合了两个分支, 分别对应着 `MVP`, `MVVM`.

* MVP分支  
架构模式: `MVP` + `Kotlin`  
网络请求: `RxJava` + `Retrofit` + `Okhttp`

* MVVM分支  
架构模式: `MVVM`+ `Androidx` + `Kotlin` + `LiveData` + `ViewModel`  
网络请求: `Coroutines` + `Retrofit` + `Okhttp`

# MVVM框架引入

## 注意！
由于采用了`Androidx`, 所以如果不是Androidx的话, 引入会有`兼容性`问题(会报错!). 

版本: minSdkVersion `19`

以下默认是`Androidx` 项目下引入

先在 build.gradle(Project:XXXX) 的 repositories 添加:
```Java
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
然后在 build.gradle(Module:app) 的 dependencies 添加:

```
implementation 'com.hyzhan:KtArmor:1.0.1'
```

# 快速上手
 在Application 中初始化`KtArmor` 框架。新建一个application 类, 如 BaseApplication, 在 BaseApplication 中, 调用KtArmor的 `init` 方法, 进行初始化, 参数如下:
 * 第一个参数是`application`,
 * 第二个参数是对应`RetrofitConfig` 配置。
 ```kotlin
 class BaseApplication: Application(){

     override fun onCreate() {
         super.onCreate()

         // 初始化KtArmor
         KtArmor.init(this, MyRetrofitConfig())
     }
 }
 ```
 再新建一个 RetrofitConfig 配置类, 继承 `BaseRetrofitConfig`. 并复写 `baseUrl` 属性, 添加自己的 baseUrl。


 ```kotlin
 class MyRetrofitConfig : BaseRetrofitConfig() {

     override val baseUrl: String
         get() = API.BASE_URL
 }
 ```
 这样你就创建好了一个拥有`Kotlin` + `MVVM` + `Retrofit` + `Okhttp` + `Coroutine`项目了。然后就可以愉快编写自己的业务代码了(●'◡'●)

# 示例项目

## [Kotlin 版 玩Android](https://github.com/hyzhan43/PlayAndroid/tree/KtArmor-MVVM)

# 未完待续~
