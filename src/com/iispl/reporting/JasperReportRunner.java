package com.iispl.reporting;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperReportRunner {

	
	
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