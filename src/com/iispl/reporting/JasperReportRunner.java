package com.iispl.reporting;

import java.sql.Connection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperReportRunner {
	public void generateDailyChequeReport() {
		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			
			JasperReport jasperReport = JasperCompileManager.compileReport("reports/daily_cheque_report.jrxml");
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,connection);
			
			JasperExportManager.exportReportToPdfFile(jasperPrint, "output/daily_cheque_report.pdf");

            System.out.println("Daily cheque report generated successfully.");
 } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
