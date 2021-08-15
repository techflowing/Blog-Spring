create table blog_config
(
    id          int auto_increment primary key comment '自增主键',
    name        varchar(255)                           not null comment '配置项名称',
    content     json comment '配置项内容，只支持JSON格式',
    `desc`      varchar(255) default '' null comment '配置项描述',
    create_time timestamp    default current_timestamp not null,
    update_time timestamp    default current_timestamp not null on update current_timestamp,
    constraint uniq_name
        unique (name)
) engine = InnoDB
  charset = utf8mb4 comment '博客站配置表';