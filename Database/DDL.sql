--Path: C:\Users\ASUS\OneDrive\Desktop\DBMS PROJECT\Database\DDL.sql

DROP TABLE bank cascade constraints;
DROP TABLE branch cascade constraints;
DROP TABLE account cascade constraints;
DROP TABLE customer cascade constraints;
DROP TABLE transaction cascade constraints;
DROP TABLE loan cascade constraints;
DROP TABLE installment cascade constraints;
DROP TABLE credential cascade constraints;

CREATE TABLE bank
(
    bank_id int,
    bank_name varchar(50) NOT NULL,
    bank_address varchar(100) NOT NULL,
    bank_email varchar(50) NOT NULL,
    bank_phone varchar(20) NOT NULL,
    PRIMARY KEY (bank_id)
);


CREATE TABLE branch
(
    branch_id int,
    branch_name varchar(50),
    branch_address varchar(100),
    branch_email varchar(50),
    branch_phone varchar(20),
    bank_id int,
    PRIMARY KEY (branch_id),
    FOREIGN KEY (bank_id) REFERENCES bank(bank_id)
);


CREATE TABLE customer
(
    customer_id int,
    customer_name varchar(50) NOT NULL,
    customer_address varchar(100),
    customer_email varchar(50),
    customer_phone varchar(20) NOT NULL,
    PRIMARY KEY (customer_id)
);

CREATE TABLE credential
(
    customer_id int,
    customer_password varchar(50),
    PRIMARY KEY (customer_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE account
(
    account_id int,
    account_type varchar(50),
    account_balance numeric(12,2),
    branch_id int,
    customer_id int,
    PRIMARY KEY (account_id),
    FOREIGN KEY (branch_id) REFERENCES branch(branch_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);



CREATE TABLE transaction
(
    transaction_id int,
    transaction_type varchar(50),
    transaction_amount numeric(12,2),
    transaction_date date,
    account_id int,
    PRIMARY KEY (transaction_id),
    FOREIGN KEY (account_id) REFERENCES account(account_id)
);


CREATE TABLE loan
(
    loan_id int,
    loan_type varchar(50),
    loan_amount numeric(12,2),
    loan_date date,
    account_id int,
    branch_id int,
    PRIMARY KEY (loan_id),
    FOREIGN KEY (account_id) REFERENCES account(account_id),
    FOREIGN KEY (branch_id) REFERENCES branch(branch_id)
);


CREATE TABLE installment
(
    installment_id int,
    installment_amount numeric(12,2),
    installment_date date,
    loan_id int,
    PRIMARY KEY (installment_id),
    FOREIGN KEY (loan_id) REFERENCES loan(loan_id)
);


--Create a trigger to customer insertion
CREATE OR REPLACE TRIGGER customer_insert
BEFORE INSERT ON customer
FOR EACH ROW
BEGIN
    :new.customer_id := generate_customer_id();
END;
/