package com.pfe.parc.informatique.security.services;

import com.pfe.parc.informatique.entities.User;
import com.pfe.parc.informatique.repository.UserRepository;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private UserRepository userRepository;

    public byte[] generateUserReport() throws Exception {
        // Fetch data
        List<User> users = userRepository.findAll();

        // Compile JRXML file
        InputStream userReportStream = getClass().getResourceAsStream("/templates/user_report.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(userReportStream),
                new HashMap<>(),
                new JRBeanCollectionDataSource(users)
        );

        // Export report to PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
