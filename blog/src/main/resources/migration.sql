insert into OneSpring.blog_wiki_project(id, name, description, doc_count, thumb, hash_key, create_time, update_time)
select id,
       name,
       description,
       doc_count,
       concat('http://ali-oss.techflowing.cn/', thumb),
       md5(concat(name, current_time, rand())),
       created_at,
       updated_at
from blog.wiki_project;


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
from blog.wiki_document;


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
from blog.xmind_map;

insert into OneSpring.blog_xmind_map(name, type, parent_id, sort, content, hash_key)
values ('专业技术', 1, 0, 1, null, md5(concat(name, current_time))),
       ('读书笔记', 1, 0, 2, null, md5(concat(name, create_time)));

update OneSpring.blog_xmind_map
set parent_id = 14
where id = 4;

update OneSpring.blog_xmind_map
set parent_id = 13
where id in (1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12);

insert into OneSpring.blog_statistic_event(date, name, scene, location, ip, content, create_time, update_time)
select date,
    name,
    scene,
    location,
    ip,
    content,
    created_at,
    updated_at
from blog.statistic_event;


insert into OneSpring.blog_statistic_visitor_pv(id, scene, location, pv, create_time, update_time)
select id, scene, location, pv, created_at, updated_at
from blog.statistic_pv;


insert into OneSpring.blog_statistic_visitor_uv(id, scene, location, uv, create_time, update_time)
select id, scene, location, uv, created_at, updated_at
from blog.statistic_uv;