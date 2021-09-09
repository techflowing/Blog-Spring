create table blog_xmind_map
(
    id          int primary key auto_increment comment '自增ID',
    name        varchar(255)                        not null comment '导图名称',
    type        int                                 not null comment '类型，1-文件夹/ 0-文档',
    parent_id   int                                 not null comment '父级ID',
    sort        int       default 0                 not null comment '排序',
    content     json null comment '文档内容',
    hash_key    varchar(255)                        not null comment 'Hash标识',
    create_time timestamp default current_timestamp not null,
    update_time timestamp default current_timestamp not null on update current_timestamp,
    constraint uniq_key
        unique (hash_key)
) engine = InnoDB
  charset = utf8mb4 comment '思维导图信息表';

insert into OneSpring.blog_xmind_map(id, name, type, parent_id, sort, content, hash_key, create_time, update_time)
select id,
       name,
       0,
       0,
       0,
       content,
       md5(concat(name, current_time, rand())),
       created_at,
       updated_at
from Blog.xmind_map;

insert into OneSpring.blog_xmind_map(name, type, parent_id, sort, content, hash_key)
values ('专业技术', 1, 0, 1, null, md5(concat(name, current_time))),
       ('读书笔记', 1, 0, 2, null, md5(concat(name, create_time)));

update OneSpring.blog_xmind_map
set parent_id = 14
where id = 4;

update OneSpring.blog_xmind_map
set parent_id = 13
where id in (1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12);