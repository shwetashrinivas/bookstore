alter table books
add author varchar(255) not null
add price varchar(255) not null
add description varchar(255) not null,
    coverImageUrl varchar(255),
    rating decimal not null,
    quanity int not null,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP,

