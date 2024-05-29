--path: C:\Users\ASUS\OneDrive\Desktop\DBMS PROJECT\Database\PL.sql


--write function to auto generate account number
CREATE or REPLACE FUNCTION generate_account_id
RETURN INT
AS 
account_number INT;
BEGIN

    SELECT MAX(account_id) into account_number FROM account;

    IF account_number IS NULL THEN
        RETURN 100001;
    ELSE
        RETURN account_number + 1;
    END IF;
END;
/



--write function to auto generate transaction number
CREATE or REPLACE FUNCTION generate_transaction_id
RETURN INT
AS
transaction_number INT;
BEGIN
    SELECT MAX(transaction_id) into transaction_number FROM TRANSACTION;
    IF transaction_number IS NULL THEN
        RETURN 000001;
    ELSE
        RETURN transaction_number + 1;
    END IF;
END;
/


--write function to auto generate loan number
CREATE or REPLACE FUNCTION generate_loan_id
RETURN INT
AS
loan_number INT;
BEGIN
    SELECT MAX(loan_id) into loan_number FROM LOAN;
    IF loan_number IS NULL THEN
        RETURN 1001;
    ELSE
        RETURN loan_number + 1;
    END IF;
END;
/



--write function to auto generate customer number
CREATE or REPLACE FUNCTION generate_customer_id
RETURN INT
AS
customer_number INT;
BEGIN
    SELECT MAX(customer_id) into customer_number FROM CUSTOMER;
    IF customer_number IS NULL THEN
        RETURN 1001;
    ELSE
        RETURN customer_number + 1;
    END IF;
END;
/


--Function to add balance to account
CREATE OR REPLACE PROCEDURE addBalanceToAccount(Id in INT, amount in INT)
AS
BEGIN
    UPDATE account
    SET account_balance = account_balance + amount
    WHERE account_id = Id;
END;
/


--Function to subtract balance from account
CREATE OR REPLACE PROCEDURE subtractBalanceFromAccount(Id in INT, amount in INT)
AS
BEGIN
    UPDATE account
    SET account_balance = account_balance - amount
    WHERE account_id = Id;
END;
/