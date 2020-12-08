create table user_subscriptions
(
    channel_id int not null references user,
    subscriber_id int not null references user,
    primary key (channel_id, subscriber_id)
)