-- Create a new database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'combat_db')
    BEGIN
        CREATE DATABASE combat_db;
    END;
GO

-- Switch context to the new database
USE combat_db;
GO


-- Create a new schema in the database
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'combat_schema')
    BEGIN
        EXEC('CREATE SCHEMA combat_schema');
    END;
GO