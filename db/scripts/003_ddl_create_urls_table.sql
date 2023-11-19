CREATE TABLE IF NOT EXISTS urls
(
    id serial primary key not null,
    url varchar(2000) NOT NULL unique,
    urlkey varchar(2000) NOT NULL unique,
    count int,
    site_id INT NOT NULL REFERENCES sites(id)
);

comment on table urls is 'Соответствие ключ-URL';
comment on column urls.id is 'Идентификатор ключ-URL';
comment on column urls.url is 'URL';
comment on column urls.urlkey is 'Ключ';
comment on column urls.count is 'Счетчик посещения ссылки';
comment on column urls.site_id is 'Сайт, которому принадлежит эта ссылка';
