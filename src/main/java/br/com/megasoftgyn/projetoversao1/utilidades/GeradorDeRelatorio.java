package br.com.megasoftgyn.projetoversao1.utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class GeradorDeRelatorio {
    
    public byte[] imprimirRelatorio(final Map<String, Object> parametros, final List<?> dataSource, final InputStream inputStream) {
        try {
            if (!parametros.containsKey("REPORT_LOCALE")) {
                parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
            }
            final JasperPrint relatorio;
            
            if (dataSource != null) {
                relatorio = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(dataSource, false));
            } else {
                relatorio = JasperFillManager.fillReport(inputStream, parametros, new JREmptyDataSource(1));
            }
            return JasperExportManager.exportReportToPdf(relatorio);
            
        } catch (final JRException ex) {
            throw new RuntimeException(ex);
            
        } finally {
            fecharInputStream(inputStream);
        }
    }
    
    private static void fecharInputStream(final InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
