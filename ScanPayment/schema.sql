create table payments (
                          id int4 generated by default as identity,
                          payment varchar(255),
                          payment_completed boolean not null,
                          type_file varchar(255),
                          primary key (id)
)