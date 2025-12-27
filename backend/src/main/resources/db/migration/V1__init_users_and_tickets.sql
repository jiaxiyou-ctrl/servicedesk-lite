create table users (
  id bigserial primary key,
  email varchar(255) not null unique,
  password_hash varchar(255) not null,
  role varchar(32) not null,
  created_at timestamptz not null default now()
);

create table tickets (
  id bigserial primary key,
  title varchar(200) not null,
  description text,
  status varchar(32) not null,
  requester_id bigint not null references users(id),
  assignee_id bigint references users(id),
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now()
);

create index idx_tickets_status on tickets(status);
create index idx_tickets_assignee_id on tickets(assignee_id);
