create table auth.user (
    id uuid primary key default gen_random_uuid(),
    name text unique not null,
    role text not null,
    password text not null,
    status text not null,
    created_at timestamp with time zone not null default CURRENT_TIMESTAMP
);