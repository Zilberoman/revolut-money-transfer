CREATE DATABASE if not exists accounts_test;

CREATE TABLE if not exists Accounts (
    Account_ID int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Account_Name varchar(255) DEFAULT NULL,
    CCY varchar(255) DEFAULT NULL,
    Balance double DEFAULT 0
);

CREATE TABLE if not exists Users (
    User_ID Int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    User_Login varchar(255) DEFAULT NULL
);

CREATE UNIQUE INDEX idx_ue on Users(User_Login);
CREATE UNIQUE INDEX idx_acc on Accounts(Account_Name, CCY);

INSERT INTO Users (User_Login) VALUES ('testingUser');
INSERT INTO Users (User_Login) VALUES ('mTestWace');
INSERT INTO Users (User_Login) VALUES ('BassoACC');

INSERT INTO Accounts (Account_Name, Balance, CCY) VALUES ('luo', 100.0000, 'USD');
INSERT INTO Accounts (Account_Name, Balance, CCY) VALUES ('l6uo', 1900.0000, 'USD');
INSERT INTO Accounts (Account_Name, Balance, CCY) VALUES ('luo9', 1070.0000, 'USD');
INSERT INTO Accounts (Account_Name, Balance, CCY) VALUES ('kluo', 100.00090, 'USD');
INSERT INTO Accounts (Account_Name, Balance, CCY) VALUES ('luocfc', 100.90000, 'USD');
