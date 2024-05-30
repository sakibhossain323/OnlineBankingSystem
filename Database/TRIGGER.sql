--path: C:\Users\ASUS\OneDrive\Desktop\DBMS PROJECT\Database\TRIGGER.sql

--Create a trigger to customer insertion
CREATE OR REPLACE TRIGGER customer_insert
BEFORE INSERT ON customer
FOR EACH ROW
BEGIN
    :new.customer_id := generate_customer_id();
END;
/


--Account Insertion
CREATE OR REPLACE TRIGGER account_insert
BEFORE INSERT ON account
FOR EACH ROW
BEGIN
    :new.account_id := generate_account_id();
END;
/



--Transaction Insertion
CREATE OR REPLACE TRIGGER transaction_insert
BEFORE INSERT ON transaction
FOR EACH ROW
BEGIN
    :new.transaction_id := generate_transaction_id();
    subtractBalanceFromAccount(:new.from_account_id, :new.transaction_amount);
    addBalanceToAccount(:new.to_account_id, :new.transaction_amount);
END;
/



--Loan Insertion
CREATE OR REPLACE TRIGGER loan_insert
BEFORE INSERT ON loan
FOR EACH ROW
BEGIN

    if(checkEligibilityForLoan(:new.account_id, :new.loan_amount)=false) then
        raise_application_error(-20001, 'Loan amount exceeds eligibility');
end if;

    :new.loan_id := generate_loan_id();

    addLoanToAccount(:new.account_id, :new.loan_amount);
END;
/


--Installment Insertion
CREATE OR REPLACE TRIGGER installment_insert
    AFTER INSERT ON loan
    FOR EACH ROW
BEGIN
    insertInstallment(:new.loan_id, :new.loan_amount, :new.duration);
END;
/