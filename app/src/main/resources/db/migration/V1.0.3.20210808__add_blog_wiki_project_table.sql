create table blog_wiki_project
(
    id          int auto_increment primary key comment '自增主键',
    name        varchar(255)                        not null comment '项目名称',
    description text null comment '项目描述',
    doc_count   int       default 0                 not null comment '文档数量',
    thumb       varchar(255)                        not null comment '项目封面图',
    hash_key    varchar(255)                        not null comment 'Hash标识',
    create_time timestamp default current_timestamp not null,
    update_time timestamp default current_timestamp not null on update current_timestamp
) engine = InnoDB
  charset = utf8mb4 comment '博客站知识库项目表';
