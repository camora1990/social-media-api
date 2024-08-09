CREATE TABLE [dbo].[User] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [name] NVARCHAR(255) NOT NULL,
    [birthDate] DATE NOT NULL
    );