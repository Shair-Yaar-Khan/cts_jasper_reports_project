package com.iispl.reporting;

import java.sql.Connection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperReportRunner {

	public void generateBatchSummaryReport() throws Exception {

	    String inputFile = "reports/batch_summary_report.jrxml";

	    JasperReport jasperReport = JasperCompileManager.compileReport(inputFile);

	    Connection connection = DBConnection.getConnection();

	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,connection);

	    JasperExportManager.exportReportToPdfFile(jasperPrint,"output/batch_summary_report.pdf");

	    System.out.println("Batch Summary Report generated successfully.");
	}
	
	public static void generateBankSummaryReport() throws Exception {

	    String inputFile = "reports/bank_summary_report.jrxml";

	    JasperReport jasperReport = JasperCompileManager.compileReport(inputFile);

	    Connection connection = DBConnection.getConnection();

	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,connection);

	    JasperExportManager.exportReportToPdfFile(jasperPrint,"output/bank_summary_report.pdf");

	    System.out.println("Bank Summary Report generated successfully.");
	    
	}
}
