create table blog_thought
(
    id          int auto_increment primary key comment '自增主键',
    title       varchar(255) default ''                not null comment '标题',
    content     mediumtext null comment '富文本Raw数据',
    html        mediumtext null comment 'HTML 内容',
    tag         json null comment 'TAG 标签，JSON 数组存储',
    hash_key    varchar(255)                           not null comment 'Hash标识',
    create_time timestamp    default current_timestamp not null,
    update_time timestamp    default current_timestamp not null on update current_timestamp
)engine = InnoDB
  charset = utf8mb4 comment '博客站随想录';