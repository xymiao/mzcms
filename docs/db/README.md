```mysql
create user mzcms IDENTIFIED by 'mzcms';
create database mzcms;
grant all on mzcms.* to 'mzcms'@'%';
```


```mysql
CREATE TABLE `mzcms`.`cms_menu` (
`menu_id` VARCHAR(32) NOT NULL COMMENT '主键',
`menu_name` VARCHAR(100) NULL COMMENT '菜单名称',
`sort` INT NULL COMMENT '排序',
`menu_info` VARCHAR(200) NULL COMMENT '菜单说明',
`parent_id` VARCHAR(32) NULL COMMENT '父类id, 默认为 parent 定义为父类' ,
`menu_type` VARCHAR(32) NULL ,
`menu_module` VARCHAR(45) NULL COMMENT '模块名称',
`del_flag` VARCHAR(2) NULL COMMENT '删除标志 1 删除  0 正常' ,
`created` DATETIME NULL COMMENT '创建时间' ,
`last_date` DATETIME NULL COMMENT '最后更新时间',
`url` VARCHAR(200) NULL COMMENT  '请求地址',
PRIMARY KEY (`menu_id`))
COMMENT = '菜单表';

```


### 用户相关的表
```mysql
CREATE TABLE `mzcms`.`sys_user` (
  `user_id` VARCHAR(32) NOT NULL COMMENT '用户主键',
  `nick_name` VARCHAR(64) NULL COMMENT '昵称',
  `login_id` VARCHAR(32) NULL COMMENT '登录用户名',
  `last_time` VARCHAR(45) NULL COMMENT '最后登录时间',
  `created` DATETIME NULL COMMENT '创建时间',
  `user_pwd` VARCHAR(128) NULL COMMENT '用户密码',
  PRIMARY KEY (`user_id`));

```

```mysql

CREATE TABLE `mzcms`.`sys_role` (
  `role_id` VARCHAR(32) NOT NULL COMMENT '角色主键',
  `role_name` VARCHAR(64) NULL COMMENT '角色名称',
  `role_desc` VARCHAR(64) NULL COMMENT '角色说明',
  PRIMARY KEY (`role_id`))
COMMENT = '角色表';

```

```mysql

CREATE TABLE `mzcms`.`sys_permission` (
  `permission_id` VARCHAR(32) NOT NULL COMMENT '主键',
  `permission_code` VARCHAR(32) NULL COMMENT '权限代码',
  `permission_name` VARCHAR(64) NULL COMMENT '权限名称',
  PRIMARY KEY (`permission_id`))
COMMENT = '权限表';

```

```mysql

CREATE TABLE `mzcms`.`sys_role_permission` (
  `role_permission_id` VARCHAR(32) NOT NULL COMMENT '主键',
  `role_id` VARCHAR(32) NULL COMMENT '角色id',
  `permission_id` VARCHAR(32) NULL COMMENT '权限id',
  PRIMARY KEY (`role_permission_id`))
COMMENT = '角色权限关联';

```

## 站点配置表
```mysql
CREATE TABLE `sys_site_setting` (
  `site_id` VARCHAR(32) NOT NULL COMMENT '主键',
  `title` VARCHAR(100) NULL COMMENT '配置名称',
  `site_key` VARCHAR(32) NULL COMMENT '配置主键',
  `site_value` VARCHAR(100) NULL COMMENT '配置值',
  `value_type` VARCHAR(32) NULL COMMENT 'value 类型  text, json ',
  `site_state` VARCHAR(1) NULL COMMENT '状态 1 有效  2 无效',
  `created` DATETIME NULL COMMENT '创建时间',
  `updated` DATETIME NULL COMMENT '修改时间',
  `user_id` VARCHAR(32) NULL COMMENT '创建人',
  `module_type` VARCHAR(32) NULL COMMENT '模块类型',
  PRIMARY KEY (`site_id`))
COMMENT = '系统站点设置表';
```