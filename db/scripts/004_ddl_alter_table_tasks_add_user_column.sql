alter table tasks
add column user_id int references users(id);