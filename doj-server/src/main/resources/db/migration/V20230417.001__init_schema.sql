create table discussion
(
    id            bigint auto_increment comment '主键'
        primary key,
    discussion_id varchar(20)     default ''  not null comment '讨论帖子 id',
    description   varchar(255)    default ''  not null comment '简介',
    user_id       varchar(20)     default ''  not null comment '用户 id',
    view_num      int             default 0   not null comment '浏览量',
    like_num      int             default 0   not null comment '喜欢数量',
    top_priority  smallint        default 0   not null comment '优先级',
    comment_num   int             default 0   not null comment '评论数量',
    status        smallint        default 0   null comment '状态',
    create_user   varchar(20)     default ''  not null comment '创建人',
    update_user   varchar(20)     default ''  not null comment '更新人',
    create_time   bigint unsigned default '0' not null comment '创建时间',
    update_time   bigint unsigned default '0' not null comment '更新时间',
    delete_time   bigint unsigned default '0' not null comment '删除时间',
    constraint uk_discussion_id
        unique (discussion_id)
)
    comment '讨论帖子';