# LoggerKnife
### 1.支持多种参数输入
### 2.支持输出良好格式（如json等）
### 3.支持超长Log内容的输出
  以3000个字符截取，无缝显示在log控制台中

# 支持输入格式
### 1. JsonObject(及Json类型的字符串)
### 2. 其它常用数据类型（不在详细列出）

# 使用方式
### 1.引入
##### A.Maven
```java
<dependency>
  <groupId>com.rdors.loggerknife.loggerknifelibrary</groupId>
  <artifactId>loggerknifelibrary</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
##### B.Gradle
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
L.i(Object object);
/**
 * 如果tagName 不为空，则以 该 tagName 为tag 输入log
 **/
L.i(String tagName,Object object);
```
