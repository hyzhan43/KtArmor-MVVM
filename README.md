# 前言
学习了Kotlin有一段时间了, 每次写`项目/Demo`的时候, 总是用到`网络请求`、`MVP`、`MVVM`、`常用工具类`、`通用自定义View`, 索性把这些整合到一起, 搭成一个Android的脚手架——`KtArmor`. 

# 什么是KtArmor ?

`KtArmor` 寓意着 为Android 赋予`战斗装甲`, 方便开发者快速进行Android 开发。节约开发者开发时间。为了满足开发者需求, 我整合了两个分支, 分别对应着 `MVP`, `MVVM`.

- MVP分支  
  - `架构模式`: `MVP` + `Kotlin`  
  - `网络请求`: `Retrofit` + `Okhttp` + `Coroutine` + ~~`RxJava`~~    
  - `代码`：简洁优雅, 易扩展
  - `文档`: 有相关的KtArmor-MVP 系列文档说明
  - `插件`：对应 KtArmor-MVP 模板代码生成插件
  - `功能`:  
    - 基本`BaseActivity`、`BaseFragment`、`ToolbarActivity`封装  
    - MVP框架封装 `MvpActivity`、`MvpFragment`、`BasePresenter`、`BaseModel`封装  
    - 网络请求封装 `BaseOkHttpClient`、`BaseRetrofit`、`RetrofitFactory`  
    - 常用控件`PlaceHolderView(占位布局)`， `LoadingView(加载框)`  
    - 常用扩展封装(`SharedPreferences`、`StartActivity`、`Log`、`Toast`(不重复显示))等  
    - MVP代码模板(`Activity`、`Presenter`、`Contract`、`Model`)生成插件  
    - ....
- MVVM分支  
架构模式: `MVVM`+ `Androidx` + `Kotlin` + `LiveData` + `ViewModel`  
网络请求: `Coroutines` + `Retrofit` + `Okhttp`

> # 注意  看这里！
>
> KtArmor 对应`源代码`在 对应分支上.  
> `master` 分支暂时没有合并。  
>
> [如 KtArmor-MVP 版，在 mvp 分支上]("https://github.com/hyzhan43/KtArmor/tree/mvp")   
> [如 KtArmor-MVVM 版，在 mvvm 分支上]("https://github.com/hyzhan43/KtArmor/tree/mvvm")   


# KtArmor-MVP 相关文档

## [Kotlin的魔能机甲——KtArmor（一）](https://juejin.im/post/5d319e3651882540be3a2be7)  

## [Kotlin的魔能机甲——KtArmor插件篇（二）](https://juejin.im/post/5d3eb9e7f265da03a31d1072)

## [Kotlin的魔能机甲——KtArmor（三）](https://juejin.im/post/5d4a872e51882575595c40f1)