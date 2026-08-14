package com.iispl.reporting;

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
}