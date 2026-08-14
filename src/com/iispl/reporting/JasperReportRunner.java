package com.iispl.reporting;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


public class JasperReportRunner {
	public void generateHighValueChequeReport() throws Exception {

        JasperReport report = JasperCompileManager.compileReport(
                "reports/high_value_report.jrxml"
        );

        Map<String, Object> parameters = new HashMap<>();

        Connection connection = DBConnection.getConnection();

        JasperPrint print = JasperFillManager.fillReport(
                report,
                parameters,
                connection
        );

        JasperExportManager.exportReportToPdfFile(
                print,
                "output/high_value_report.pdf"
        );
        
        System.out.println("High Value Cheque Report generated successfully.");

        connection.close();
    }
    public void micrRepairReport() throws SQLException, JRException
    {
    	

            Connection connection =
                    DBConnection.getConnection();

            System.out.println("Database connected successfully.");

            String jrxmlFile =
                    "reports/micr_repair_report.jrxml";

            JasperReport jasperReport =
                    JasperCompileManager.compileReport(jrxmlFile);

            System.out.println("JRXML compiled successfully.");


         

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            null,
                            connection
                    );

            System.out.println("Report filled successfully.");


            String outputFile =
                    "output/micr_repair_report.pdf";

            JasperExportManager.exportReportToPdfFile(
                    jasperPrint,
                    outputFile
            );

            System.out.println(
                    "PDF generated successfully:"
            );

            System.out.println(outputFile);

    }


	public void generateDailyChequeReport() {
		Connection connection = null;
		
			try {
				connection = DBConnection.getConnection();
				
				JasperReport jasperReport = JasperCompileManager.compileReport("reports/daily_cheque_report.jrxml");
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,connection);
				
				JasperExportManager.exportReportToPdfFile(jasperPrint, "output/daily_cheque_report.pdf");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(JRException e) {
				e.printStackTrace();
			}
	        System.out.println("Daily Cheque Report generated successfully.");

			
  }
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
	
	public void generateRejectedChequeReport() throws Exception{

        try {
            Connection connection =
                    DBConnection.getConnection();

          
            InputStream reportStream =
                    new FileInputStream(
                            "reports/rejected_cheque_report.jrxml");

            JasperReport jasperReport =
                    JasperCompileManager.compileReport(
                            reportStream);
            Map<String, Object> parameters =
                    new HashMap<>();

          
            parameters.put(
                    "P_STATUS",
                    "REJECTED" );

          
            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            parameters,
                            connection );

            JasperExportManager.exportReportToPdfFile(
                    jasperPrint,
                    "output/rejected_cheque_report.pdf");

           
            reportStream.close();
            connection.close();

            System.out.println(
                    "Report generated successfully!");

        } 
        catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}

