CREATE TABLE IF NOT EXISTS urls
(
    id serial primary key not null,
    url varchar(2000) NOT NULL unique,
    urlKey varchar(2000) NOT NULL unique
);

comment on table urls is 'Соответствие ключ-URL';
comment on column urls.id is 'Идентификатор ключ-URL';
comment on column urls.url is 'URL';
comment on column urls.urlKey is 'Ключ';
