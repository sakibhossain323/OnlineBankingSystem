--write function to auto generate account number
CREATE or REPLACE FUNCTION generate_account_number()
RETURNS INT
AS 
ACCOUNT_NUMBER INT;
BEGIN
    SET account_number = (SELECT MAX(account_id) FROM ACCOUNT);
    IF account_number IS NULL THEN
        RETURN 1001;
    ELSE
        RETURN account_number + 1;
    END IF;
END;



--write function to auto generate transaction number
CREATE or REPLACE FUNCTION generate_transaction_number()
RETURNS INT
AS
TRANSACTION_NUMBER INT;
BEGIN
    SET transaction_number = (SELECT MAX(transaction_id) FROM TRANSACTION);
    IF transaction_number IS NULL THEN
        RETURN 1001;
    ELSE
        RETURN transaction_number + 1;
    END IF;
END;


--write function to auto generate loan number
CREATE or REPLACE FUNCTION generate_loan_number()
RETURNS INT
AS
LOAN_NUMBER INT;
BEGIN
    SET loan_number = (SELECT MAX(loan_id) FROM LOAN);
    IF loan_number IS NULL THEN
        RETURN 1001;
    ELSE
        RETURN loan_number + 1;
    END IF;
END;



--write function to auto generate customer number
CREATE or REPLACE FUNCTION generate_customer_number()
RETURNS INT
AS
CUSTOMER_NUMBER INT;
BEGIN
    SET customer_number = (SELECT MAX(customer_id) FROM CUSTOMER);
    IF customer_number IS NULL THEN
        RETURN 1001;
    ELSE
        RETURN customer_number + 1;
    END IF;
END;


