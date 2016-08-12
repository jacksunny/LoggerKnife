# github 地址
  [传送门](https://github.com/jacksunny/LoggerKnife/)
# LoggerKnife
 1. 支持多种参数输入,且使用方便
 2. 支持输出良好格式（如json等）
 3. 支持超长Log内容的输出
 > 以3000个字符截取，无缝显示在log控制台中
 

# 支持输入格式
 1. JsonObject(及Json类型的字符串)
 2. 其它常用数据类型（不在详细列出）

# 输出样式
 
1. json
![](http://7xpc6d.com1.z0.glb.clouddn.com/1.1version.png)
2. 其他
   不举例
 
# 使用方式
 
### 1.引入

Android Studio 同学推荐B方案

Eclipse 同学 最快的方式为 下载源码（两个文件）

（！如出现错误请参照本文FAQ）

A. Maven
 
```java
<dependency>
  <groupId>com.rdors.loggerknife.loggerknifelibrary</groupId>
  <artifactId>loggerknifelibrary</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
 B. Gradle
```java
compile 'com.rdors.loggerknife.loggerknifelibrary:loggerknifelibrary:1.0.0'
```
### 2.初始化（可忽略）
 
```java
/**
 * 如果不调用本方法，则默认输出所有级别的log，且输出 tag 为调用类名
 * @param logLevel 输入级别控制，如果为空，则输出所有级别   
 * @param tagName log 全局 tag 名称，如果为空，则 输出 tag 为调用类名
 **/
L.init(LogLevel,logLevel,String tagName);
```
### 3.使用
```java
/**
 * 不指定tag，以 默认类名或者 制定全局tag 为tag 输出log
 **/
L.i(Object object);
/**
 * 如果tagName 不为空，则以 该 tagName 为tag 输入log
 **/
L.i(String tagName,Object object);
```

# 设置AndroidStudio的log 颜色
如下，需要“Save as”保存主题，然后取消“use inherited attributes”，即可设置自己想要的颜色 
![](http://i5.tietuku.com/d103d5dc32e55695.png)

我的配色方案：

* A=FF2E0E
* D=38F838
* E=FF6B68
* I=1DBB92
* V=FFF7EE
* W=FF9229
 
# 参考
 
* [设置Android Studio log颜色部分参考《Android专用Log开源项目——KLog》](http://blog.csdn.net/zhaokaiqiang1992/article/details/49837627)
* [Android的LogCat的使用，调试规范Log](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2012/1019/445.html)
 
# FAQ
1. 引入后出现错误
   “Suggestion: add 'tools:replace="theme"' to <application> element at AndroidManifest.xml:29:5 to override” 等
   解决方案：在最外层AndroidManifest.xml ，application标签中加入以下即可
```java
// 如果提示 icon等同理
tools:replace="theme"
```

# 版本更新说明
1.0
1. 输出格式化的json
2. 用 “==”作为横向隔离
3. 用“|”作为纵向隔离
4. 可以在 AndroidStudio中直接点击调用来源

1.1
1. 删除 “|”纵向隔离，使可以直接拷贝格式化输出的json
2. 为 逗号做了处理，如果判断为一个语句中的，则不换行，在输出json时更精确

# 备注
所有源码均未参考其他类似工具（如kLog等），只有部分参考输出格式，同学们可放心使用
