CREATE TABLE IF NOT EXISTS sites
(
    id serial primary key not null,
    login varchar(2000) NOT NULL unique,
    password varchar(2000)
);

comment on table sites is 'Сайты';
comment on column sites.id is 'Идентификатор сайта';
comment on column sites.login is 'Логин сайта';
comment on column sites.password is 'Пароль сайта';
