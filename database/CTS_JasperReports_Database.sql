-- CTS JasperReports Research Assignment
-- Database: MySQL
-- Purpose: Database schema and sample data for JasperReports research work

CREATE DATABASE IF NOT EXISTS cts_reporting;
USE cts_reporting;

DROP TABLE IF EXISTS cheque_transaction;

CREATE TABLE cheque_transaction (
    cheque_id INT PRIMARY KEY,
    cheque_number VARCHAR(20) NOT NULL,
    account_number VARCHAR(20) NOT NULL,
    customer_name VARCHAR(100),
    bank_name VARCHAR(100),
    branch_name VARCHAR(100),
    micr_code VARCHAR(20),
    cheque_amount DECIMAL(12,2),
    cheque_date DATE,
    batch_id VARCHAR(20),
    cheque_type VARCHAR(30),
    status VARCHAR(30),
    micr_status VARCHAR(30),
    rejection_reason VARCHAR(200),
    processing_date DATE
);

INSERT INTO cheque_transaction VALUES
(1,'CHQ10001','ACC1001','Rahul Sharma','HDFC Bank','Panaji','403240002',25000.00,'2026-08-10','BATCH001','NORMAL','APPROVED','VALID',NULL,'2026-08-13'),
(2,'CHQ10002','ACC1002','Priya Nair','ICICI Bank','Vasco','403229003',175000.00,'2026-08-10','BATCH001','HIGH_VALUE','APPROVED','VALID',NULL,'2026-08-13'),
(3,'CHQ10003','ACC1003','Anil Kumar','SBI','Margao','403002004',45000.00,'2026-08-11','BATCH001','NORMAL','REJECTED','VALID','Insufficient Funds','2026-08-13'),
(4,'CHQ10004','ACC1004','Sneha Rao','Axis Bank','Mapusa','403211005',95000.00,'2026-08-11','BATCH002','NORMAL','MICR_REPAIR','INVALID',NULL,'2026-08-13'),
(5,'CHQ10005','ACC1005','Thomas Mathew','Federal Bank','Ponda','403049006',350000.00,'2026-08-12','BATCH002','HIGH_VALUE','APPROVED','VALID',NULL,'2026-08-13'),
(6,'CHQ10006','ACC1006','Meera Iyer','Canara Bank','Panaji','403015007',18000.00,'2026-08-12','BATCH002','NORMAL','REJECTED','VALID','Signature Mismatch','2026-08-13'),
(7,'CHQ10007','ACC1007','Arjun Singh','Bank of Baroda','Vasco','403012008',220000.00,'2026-08-12','BATCH003','HIGH_VALUE','APPROVED','VALID',NULL,'2026-08-13'),
(8,'CHQ10008','ACC1008','Neha Kapoor','Punjab National Bank','Margao','403024009',56000.00,'2026-08-12','BATCH003','NORMAL','MICR_REPAIR','INVALID',NULL,'2026-08-13'),
(9,'CHQ10009','ACC1009','Vivek Menon','HDFC Bank','Panaji','403240002',78000.00,'2026-08-13','BATCH003','NORMAL','APPROVED','VALID',NULL,'2026-08-13'),
(10,'CHQ10010','ACC1010','Sara Khan','ICICI Bank','Vasco','403229003',425000.00,'2026-08-13','BATCH003','HIGH_VALUE','REJECTED','VALID','Account Blocked','2026-08-13'),
(11,'CHQ10011','ACC1011','Rohan Das','SBI','Margao','403002004',65000.00,'2026-08-13','BATCH004','NORMAL','APPROVED','VALID',NULL,'2026-08-13'),
(12,'CHQ10012','ACC1012','Lakshmi Pillai','Federal Bank','Ponda','403049006',275000.00,'2026-08-13','BATCH004','HIGH_VALUE','APPROVED','VALID',NULL,'2026-08-13'),
(13,'CHQ10013','ACC1013','Sameer Khan','Axis Bank','Mapusa','403211005',32000.00,'2026-08-13','BATCH004','NORMAL','REJECTED','VALID','Cheque Date Invalid','2026-08-13'),
(14,'CHQ10014','ACC1014','Divya Nair','HDFC Bank','Panaji','403240002',87000.00,'2026-08-13','BATCH005','NORMAL','MICR_REPAIR','INVALID',NULL,'2026-08-13'),
(15,'CHQ10015','ACC1015','Joseph George','SBI','Vasco','403002010',510000.00,'2026-08-13','BATCH005','HIGH_VALUE','APPROVED','VALID',NULL,'2026-08-13');

-- Queries for individual reports

-- Member 1: Daily Cheque Processing Report
SELECT cheque_number, customer_name, bank_name, cheque_amount, batch_id, status
FROM cheque_transaction
WHERE processing_date = '2026-08-13';

-- Member 2: Rejected Cheque Report
SELECT cheque_number, customer_name, account_number, cheque_amount, rejection_reason
FROM cheque_transaction
WHERE status = 'REJECTED';

-- Jasper parameter version:
-- SELECT cheque_number, customer_name, account_number, cheque_amount, rejection_reason
-- FROM cheque_transaction
-- WHERE status = $P{P_STATUS};

-- Member 3: MICR Repair Report
SELECT cheque_number, bank_name, branch_name, micr_code, cheque_amount, micr_status
FROM cheque_transaction
WHERE micr_status = 'INVALID';

-- Member 4: High Value Cheque Report
SELECT cheque_number, customer_name, bank_name, cheque_amount, status
FROM cheque_transaction
WHERE cheque_amount >= 200000;

-- Member 5: Batch Processing Summary
SELECT batch_id, COUNT(*) AS total_cheques, SUM(cheque_amount) AS total_amount
FROM cheque_transaction
GROUP BY batch_id
ORDER BY batch_id;

-- Member 6: Bank-wise Processing Summary
SELECT bank_name, COUNT(*) AS total_cheques, SUM(cheque_amount) AS total_amount
FROM cheque_transaction
GROUP BY bank_name
ORDER BY total_amount DESC;

-- Optional Team Challenge: CTS Daily Operations Dashboard
SELECT
    COUNT(*) AS total_cheques,
    SUM(CASE WHEN status = 'APPROVED' THEN 1 ELSE 0 END) AS approved_cheques,
    SUM(CASE WHEN status = 'REJECTED' THEN 1 ELSE 0 END) AS rejected_cheques,
    SUM(CASE WHEN micr_status = 'INVALID' THEN 1 ELSE 0 END) AS micr_repair_pending,
    SUM(CASE WHEN cheque_amount >= 200000 THEN 1 ELSE 0 END) AS high_value_cheques,
    SUM(cheque_amount) AS total_processing_amount
FROM cheque_transaction
WHERE processing_date = '2026-08-13';
