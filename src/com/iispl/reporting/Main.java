package com.iispl.reporting;

import java.sql.Connection;

import java.sql.SQLException;
import net.sf.jasperreports.engine.JasperReport;

public class Main {
	
public static void main(String[] args) {
	
	JasperReportRunner runner = new JasperReportRunner();
	
	try {
		runner.micrRepairReport();
		runner.generateHighValueChequeReport();
		runner.generateDailyChequeReport();
		runner.generateBatchSummaryReport();
		runner.generateBankSummaryReport();
		runner.generateRejectedChequeReport();
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}
}
