import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.time.LocalDate;

public class SalaryHtmlReportNotifier {

    private final SalaryReportBuilder reportBuilder;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this.reportBuilder = new SalaryReportBuilder(databaseConnection);
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {

        String htmlReport = reportBuilder.buildHtmlReport(departmentId, dateFrom, dateTo);

        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("mail.google.com");

            MimeMessage message = buildMimeMessage(recipients, htmlReport, mailSender);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private MimeMessage buildMimeMessage(String recipients, String htmlReport, JavaMailSenderImpl mailSender) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipients);

        helper.setText(htmlReport, true);
        helper.setSubject("Monthly department salary report");
        return message;
    }
}