create table blog_wiki_document
(
    id          int primary key auto_increment comment '文档ID',
    project_id  int                                 not null comment '所属项目ID',
    name        varchar(255)                        not null comment '文档名称',
    type        int                                 not null comment '类型，1-文件夹/ 0-文档',
    parent_id   int                                 not null comment '父级ID',
    sort        int       default 0                 not null comment '排序',
    content     longtext null comment '文档内容',
    hash_key    varchar(255)                        not null comment 'Hash标识',
    create_time timestamp default current_timestamp not null,
    update_time timestamp default current_timestamp not null on update current_timestamp,
    constraint uniq_key
        unique (hash_key)
) engine = InnoDB
  charset = utf8mb4 comment '博客站知识库文章表';

insert into OneSpring.blog_wiki_document(id, project_id, name, type, parent_id, sort, content, hash_key, create_time, update_time)
select id,
       project_id,
       name,
       type,
       parent_id,
       sort,
       replace(content,'http://techflowing.cn/media-store/wiki/img/','http://ali-oss.techflowing.cn/media-store/wiki/img/'),
       md5(concat(name, current_time, rand())),
       created_at,
       updated_at
from Blog.wiki_document;