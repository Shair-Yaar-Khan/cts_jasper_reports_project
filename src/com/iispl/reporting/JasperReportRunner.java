package com.iispl.reporting;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

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
}