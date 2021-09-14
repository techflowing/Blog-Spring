create table blog_statistic_event
(
    id          int primary key auto_increment comment '自增ID',
    date        varchar(255)                        not null comment '访问日期，格式 Y-m-d',
    name        varchar(255)                        not null comment '统计时间名称',
    scene       varchar(255)                        not null comment '场景值',
    location    varchar(255)                        not null comment '位置（二级分类）',
    ip          varchar(255) null comment '用户IP',
    content     json null comment '自定义统计内容',
    create_time timestamp default current_timestamp not null,
    update_time timestamp default current_timestamp not null on update current_timestamp
) engine = InnoDB
  charset = utf8mb4 comment '统计事件细表';

insert into OneSpring.blog_statistic_event(date, name, scene, location, ip, content, create_time, update_time)
select date,
    name,
    scene,
    location,
    ip,
    content,
    created_at,
    updated_at
from Blog.statistic_event;