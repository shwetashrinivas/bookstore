alter table books
    add column author varchar(255) not null,
    add column price decimal not null,
    add column description text not null,
    add column cover_image_url varchar(255),
    add column rating decimal not null,
    add  column quantity int not null,
    add  column created_at TIMESTAMP,
    add column updated_at TIMESTAMP;

