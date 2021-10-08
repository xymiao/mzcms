# 接口文档

[TOC]



<center>MZCMS 内容管理系统接口规范</center>

| 编写人 |  更新日期  |  版本  | 备注 |
| :----: | :--------: | :----: | :--: |
| XyMiao | 2021-10-01 | V0.0.1 |  无  |



## 域名规范

接口有对应的开发接口域名和线上实际部署域名请对应区分。 

举例： 

```java
// 开发环境
String URL_DEV = "https://127.0.0.1:8080/";
// 线上环境
String URL_PROD = "https://produrl.com/"
```



## API路径约定

所有接口路径， 为了能够统一管理都使用  api 为前缀， 做好前缀统一， 好进行权限控制和拆分。

举例：

```java
// 开发环境的 api 前缀路径
String URL_DEV_API = URL_DEV + "api/"
// 线上环境的 api 前缀路径
String URL_PROD_API = URL_PROD + "api/"    
```

## 版本控制约定

所有的接口都填写对应的版本信息， 这样在进行版本迭代的时候， 不会出现接口来回打架的问题。 

约定如下:

```java
// 开发环境的接口版本路径
String URL_DEV_API_V1 = URL_DEV_API + "v1/";
// 线上环境的接口版本路径
String URL_PROD_API_V1 = URL_PROD_API + "v1/" 
```

## API命名规范

在进行 API 命名的时候， 先使用业务的类别进行区分。 然后在进行具体的接口编写。

举个例子, 用户登录的接口：

```java
// 开发环境的 v1 版本， 关于用户的登录接口。
String URL_DEV_API_V1_USER_LOGIN = URL_DEV_API_V1 + "user/login";
// 线上环境的 v1 版本， 关于用户的登录接口。
String URL_PROD_API_V1_USER_LOGIN = URL_PROD_API_V1 + "user/login";
```

**注意**：变量只是为了理解， 实际定义的时候可以抽出来一个基类用于使用。



# 项目接口

## 菜单管理

### 新增后台菜单

URL:  `URL_DEV_API_V1 + menu/add_backend`

支持格式： `JSON`

请求方式： `POST`

请求参数

|   参数   | 必选 |  类型  |    说明     |
| :------: | :--: | :----: | :---------: |
| menuName |  是  | 字符串 |  菜单名称   |
|   url    |  否  | 字符串 |  跳转地址   |
| menuDesc |  否  | 字符串 |  菜单说明   |
|   sort   |  否  |  数值  | 排序 字典序 |
|   icon   |  否  | 字符串 |  菜单图标   |

返回字段

| 字段 | 类型 | 说明 |
| :--: | :--: | :--: |
|      |      |      |

接口示例

