create table auth.user (
    id text primary key,
    name text unique not null,
    authority text not null,
    password text not null,
    enabled boolean not null,
    created_at timestamp with time zone not null
);