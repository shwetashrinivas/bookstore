package com.idfc.bootcamp.bookstore.integration;
import org.testcontainers.containers.PostgreSQLContainer;
public class PostgresTestContainer extends PostgreSQLContainer<PostgresTestContainer> {
    public static final String IMAGE_VERSION = "postgres:11.1";
    public static final String DATABASE_NAME = "test";
    public static PostgreSQLContainer container;
    public PostgresTestContainer() {
        super(IMAGE_VERSION);
    }
    public static PostgreSQLContainer getInstance() {
        if (container == null) {
            container = new PostgresTestContainer().withDatabaseName(DATABASE_NAME);
        }
        return container;
    }
    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DATABASE_USERNAME", container.getUsername());
        System.setProperty("DATABASE_PASSWORD", container.getPassword());
    }
    @Override
    public void stop() {
    }
}









