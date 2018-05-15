-- pre-execution script (should return 13 records)
SELECT count(*) FROM BankInfoLog WHERE EmployeeId = 0

-- execution script backup 13 records
SELECT * FROM BankInfoLog WHERE EmployeeId = 0

-- Delete the 13 records
DELETE BankInfoLog WHERE EmployeeId = 0

-- post-execution count should be 0
SELECT count(*) FROM BankInfoLog WHERE EmployeeId = 0eeId 