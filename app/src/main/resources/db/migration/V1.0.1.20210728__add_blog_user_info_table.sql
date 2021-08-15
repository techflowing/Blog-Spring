create table blog_user_info
(
    id          int auto_increment primary key comment '自增主键',
    username    varchar(255)                           not null comment '用户名',
    password    varchar(255)                           not null comment '密码',
    mobile      varchar(20)  default '' null comment '手机号',
    email       varchar(255) default '' null comment '邮箱',
    avatar      varchar(512) default '' null comment '头像地址',
    role        int          default 0                 not null comment '角色，角色表ID',
    create_time timestamp    default current_timestamp not null,
    update_time timestamp    default current_timestamp not null on update current_timestamp,
    constraint uniq_username
        unique (username)
) engine = InnoDB
  charset = utf8mb4 comment '博客站用户信息表';

-- 插入首条数据
insert into blog_user_info(username, password, role, create_time)
select 'admin',
       '64839ced89d4fe72f25319e00671cb91',
       id,
       '2021-07-29 01:22:27'
from blog_user_role
where level = 999 limit 1