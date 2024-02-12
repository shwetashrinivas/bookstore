alter table books
    add column author varchar(255) not null,
    add column price decimal not null,
    add column description text not null,
    add column  coverImageUrl varchar(255),
    add column rating decimal not null,
    add  column quanity int not null,
    add  column createdAt TIMESTAMP,
    add column updatedAt TIMESTAMP;

