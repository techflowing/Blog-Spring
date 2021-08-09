create table blog_wiki_project
(
    id          int auto_increment primary key comment '自增主键',
    name        varchar(255)                        not null comment '项目名称',
    description text null comment '项目描述',
    doc_count   int       default 0                 not null comment '文档数量',
    thumb       varchar(255)                        not null comment '项目封面图',
    create_time timestamp default current_timestamp not null,
    update_time timestamp default current_timestamp not null
) engine = InnoDB
  charset = utf8mb4 comment '博客站知识库项目表';

-- insert into OneSpring.blog_wiki_project(id, name, description, doc_count, thumb, create_time, update_time)
-- select id,
--        name,
--        description,
--        doc_count,
--        concat('http://ali-oss.techflowing.cn/', thumb),
--        created_at,
--        updated_at
-- from Blog.wiki_project
