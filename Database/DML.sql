INSERT INTO bank (bank_id, bank_name, bank_address, bank_email, bank_phone)
VALUES (101, 'First National Bank', '100 Main St, Anytown, USA', 'contact@fnb.com', '555-1234');

INSERT INTO bank (bank_id, bank_name, bank_address, bank_email, bank_phone)
VALUES (102, 'Second City Bank', '200 Second St, Othertown, USA', 'support@scb.com', '555-5678');


INSERT INTO branch (branch_id, branch_name, branch_address, branch_email, branch_phone, bank_id)
VALUES (1, 'Main Branch', '123 Main St, Anytown, USA', 'mainbranch@bank.com', '123-456-7890', 101);

INSERT INTO branch (branch_id, branch_name, branch_address, branch_email, branch_phone, bank_id)
VALUES (2, 'Downtown Branch', '456 Elm St, Othertown, USA', 'downtown@bank.com', '987-654-3210', 102);

INSERT INTO CUSTOMER (customer_id, customer_name, customer_address, customer_email, customer_phone)
VALUES (1001,'khalid','Dhaka','khalid@gmail.com',123);

INSERT INTO credential (customer_id, customer_password)
VALUES (1001, 'fxik');

INSERT INTO account (account_id, account_type, account_balance, branch_id, customer_id)
VALUES (10001, 'Savings', 100000.00, 1, 1001);

INSERT INTO account (account_id, account_type, account_balance, branch_id, customer_id)
VALUES (10002, 'Current', 200000.00, 2, 1001);