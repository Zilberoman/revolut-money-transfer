CREATE DATABASE accounts;

CREATE TABLE Accounts (
  Account_ID int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  Account_Name varchar(255) DEFAULT NULL,
  CCY varchar(255) DEFAULT NULL,
  Balance double DEFAULT 0
)

CREATE TABLE Users (
  User_ID Int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  User_Login varchar(255) DEFAULT NULL
)