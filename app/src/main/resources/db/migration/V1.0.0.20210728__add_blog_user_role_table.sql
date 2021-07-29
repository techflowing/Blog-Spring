create table blog_user_role
(
    id     int auto_increment primary key comment '自增主键',
    level  int          default 0    not null comment '权限级别，数字越大表示权限越大',
    `desc` varchar(255) default '普通' not null comment '权限描述',
    constraint uniq_level
        unique (level)
)engine = InnoDB
  charset = utf8mb4 comment '博客站用户权限表';

-- 插入原始数据
insert into blog_user_role(level, `desc`)
values (0, '普通'),
       (100, '管理员'),
       (999, '超级管理员');