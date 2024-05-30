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


--write function to auto generate installment id
CREATE or REPLACE FUNCTION generate_installment_id
RETURN INT
AS
installment_number INT;
BEGIN
    SELECT MAX(installment_id) into installment_number FROM INSTALLMENT;
    IF installment_number IS NULL THEN
        RETURN 1001;
    ELSE
        RETURN installment_number + 1;
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


--Procedure to add Loan to account
CREATE OR REPLACE PROCEDURE addLoanToAccount(Id in INT, amount in INT)
AS
BEGIN
    UPDATE account
        SET account_balance = account_balance + amount
        WHERE account_id = Id;
END;
/



--Function to check eligibility to take loan
CREATE OR REPLACE FUNCTION checkEligibilityForLoan(Id in INT, amount in INT)
RETURN BOOLEAN
AS
current_balance INT;
BEGIN
    SELECT account_balance INTO current_balance
    FROM account
    WHERE account_id = Id;

    IF current_balance >= amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
END;
/



--Function to insert installment of loan
CREATE OR REPLACE PROCEDURE insertInstallment(Id in INT, amount in INT, duration in INT)
AS
    interest_rate DECIMAL(5,2):=0.08;
    total_payable DECIMAL(10,2);
    monthly_installment DECIMAL(10,2);
    due_date DATE:=SYSDATE;
BEGIN
    total_payable := amount + (amount * interest_rate);
    monthly_installment := total_payable / duration;

    FOR i IN 1..duration LOOP
        INSERT INTO installment VALUES(generate_installment_id(), monthly_installment, due_date, Id);
        due_date := ADD_MONTHS(due_date, 1);
    END LOOP;
END;
/