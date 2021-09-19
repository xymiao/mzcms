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
PRIMARY KEY (`menu_id`))
COMMENT = '菜单表';

```
