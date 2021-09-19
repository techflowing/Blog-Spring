create table blog_statistic_visitor_uv
(
    id          int primary key auto_increment comment '自增ID',
    scene       varchar(255)                        not null comment '场景值',
    location    varchar(255)                        not null comment '位置（二级分类）',
    uv          int       default 0                 not null comment 'UV数',
    create_time timestamp default current_timestamp not null,
    update_time timestamp default current_timestamp not null on update current_timestamp,
    constraint uniq_scene_location
        unique (scene, location)
) engine = InnoDB
  charset = utf8mb4 comment '站点访问UV数';