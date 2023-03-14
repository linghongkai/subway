# 某一站点的信息
CREATE TABLE `site_info`(
    `id` int not null AUTO_INCREMENT,
    `name` VARCHAR(128) not null DEFAULT '', # 站点的名字
    `Abstract` varchar(2048) not null default '', # 站点的摘要信息，介绍站点
    primary key (`id`)
)engine=InnoDB AUTO_INCREMENT=8809 DEFAULT CHARSET=utf8mb3;


# 每两个站点的的关系,表明两个站点直接相连
create TABLE `site_relationship`(

    `id` int not null AUTO_INCREMENT,
    `from_id` int not null DEFAULT '0', # 这条边的端点
    to_id int not null DEFAULT '0', # 这条边的端点
    `name` varchar(128) not null default '', # 这条边的名字
    subway_id int not null default '0',
    `distance` int not null default '1', # 这条边的距离
    primary key (`id`)
)engine=InnoDB AUTO_INCREMENT=8809 DEFAULT CHARSET=utf8mb3;

# 站点，所属的地铁线。也就是，哪条地铁线包括这个站点
create table `site_subwayRoute`(
    `id` int not null auto_increment,
    site_id int not null default '0', # 站点的id
    subway_id int not null default '0', # 地铁线的id
    primary key (`id`)
)engine=InnoDB auto_increment=8809 default charset =utf8mb3;


# 关于地铁这一条线的信息，
create table `subway_info`(
    `id` int not null auto_increment,
    `name` varchar(128) not null default '',#地铁线的名字
    site_size int not null default '0', #这条地铁线一共有多少个站点
    start_site_id int not null default '0', # 这条地铁线的起点
    end_site_id int not null default '0',  #这条地铁线的终点
    primary key (`id`)
)engine=InnoDB AUTO_INCREMENT=8809 DEFAULT CHARSET=utf8mb3;

# 站点信息
insert into site_info(`name`, Abstract) VALUES ('嘉华路','嘉华路摘要');
insert into site_info(`name`, Abstract) VALUES ('南位站','南位站摘要');
insert into site_info(`name`, Abstract) VALUES ('仓丰路','仓丰路摘要');
insert into site_info(`name`, Abstract) VALUES ('塔云站','塔云站摘要');
insert into site_info(`name`, Abstract) VALUES ('石家庄站','石家庄站摘要');
insert into site_info(`name`, Abstract) VALUES ('元村站','元村站摘要');
insert into site_info(`name`, Abstract) VALUES ('北国商城','北国商城摘要');
insert into site_info(`name`, Abstract) VALUES ('义堂站','义堂站摘要');
insert into site_info(`name`, Abstract) VALUES ('柳辛庄','柳辛庄摘要');


# 站点关系
insert into site_relationship(from_id, to_id, subway_id)VALUES (8809, 8810, 8809);
insert into site_relationship(from_id, to_id, subway_id)VALUES (8810, 8811, 8809);
insert into site_relationship(from_id, to_id, subway_id)VALUES (8811, 8812, 8809);
insert into site_relationship(from_id, to_id, subway_id)VALUES (8812, 8813, 8809);
insert into site_relationship(from_id, to_id, subway_id)VALUES (8813, 8814, 8809);
insert into site_relationship(from_id, to_id, subway_id)VALUES (8814, 8815, 8809);
insert into site_relationship(from_id, to_id, subway_id)VALUES (8815, 8816, 8809);
insert into site_relationship(from_id, to_id, subway_id)VALUES (8816, 8817, 8809);


# 站点所属路线
insert into site_subwayroute(site_id, subway_id) VALUES (8809,8809);
insert into site_subwayroute(site_id, subway_id) VALUES (8810,8809);
insert into site_subwayroute(site_id, subway_id) VALUES (8811,8809);
insert into site_subwayroute(site_id, subway_id) VALUES (8812,8809);
insert into site_subwayroute(site_id, subway_id) VALUES (8813,8809);
insert into site_subwayroute(site_id, subway_id) VALUES (8814,8809);
insert into site_subwayroute(site_id, subway_id) VALUES (8815,8809);
insert into site_subwayroute(site_id, subway_id) VALUES (8816,8809);
insert into site_subwayroute(site_id, subway_id) VALUES (8817,8809);


# 地铁线路
insert into subway_info(name, site_size, start_site_id, end_site_id) VALUES ('地铁2号线',9,8809,8817);


select * from site_info;
select * from subway_info;
select * from site_subwayroute;
select * from site_relationship;



drop table site_relationship;
drop table subway_info;
drop table site_info;
drop table site_subwayroute;