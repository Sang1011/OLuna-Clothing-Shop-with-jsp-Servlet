/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import javax.mail.Authenticator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import shopping.ProductDTO;

/**
 *
 * @author Sang1011
 */
public class Email {

    // Sang101174@gmail.com
    // pass: sazeczetjesdcosr
    private static final String FROM = "yourEmail@gmail.com";
    private static final String PASSWORD = "yourpassword";

    public static boolean sendEmail(String to, String subject, String content) {
        // Khai báo thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host
        props.put("mail.smtp.post", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        // create authenticatior

        Authenticator auth = new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(FROM, PASSWORD);
            }

        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        try {
            // kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Nguoi gui
            msg.setFrom(FROM);

            // nguoi nhan
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // tieu de
            msg.setSubject(subject);

            // Quy dinh ngay gui
            msg.setSentDate(new Date());

            // quy dinh email nhan response
            // msg.setReplyTo(InternetAddress.parse(to, false));
            // noi dung
            msg.setContent(content, "text/HTML; charset=UTF-8");

            // gui mail
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            System.out.println("Gap loi trong qua trinh gui mail");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendBill(String to, String subject, List<ProductDTO> productList) {
        // Khai báo thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host
        props.put("mail.smtp.post", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        // create authenticatior

        String bill = "<html>"
                + "<head>"
                + "<style>"
                + "table { width: 100%; border-collapse: collapse; }"
                + "th, td { border: 1px solid black; padding: 8px; text-align: left; }"
                + "th { background-color: #f2f2f2; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<h2>Bill</h2>"
                + "<table>"
                + "<tr>"
                + "<th>Name</th>"
                + "<th>Price</th>"
                + "<th>Quantity</th>"
                + "<th>Total</th>"
                + "</tr>";
        double total = 0;
        for (ProductDTO p : productList) {
            double subtotal = p.getPrice() * p.getQuantity();
            total += subtotal;
            bill += "<tr>"
                    + "<td>" + p.getName() + "</td>"
                    + "<td>" + p.getPrice() + "</td>"
                    + "<td>" + p.getQuantity() + "</td>"
                    + "<td>" + subtotal + "</td>"
                    + "</tr>";
        }

        bill += "<tr>"
                + "<td colspan='3' style='text-align:right;'><strong>Grand Total</strong></td>"
                + "<td>" + total + "</td>"
                + "</tr>";

        bill += "</table>"
                + "<br>"
                + "<p>Thank you for purchasing at our shop!</p>"
                + "</body>"
                + "</html>";

        Authenticator auth = new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(FROM, PASSWORD);
            }

        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        try {
            // kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Nguoi gui
            msg.setFrom(FROM);

            // nguoi nhan
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // tieu de
            msg.setSubject(subject);

            // Quy dinh ngay gui
            msg.setSentDate(new Date());

            // quy dinh email nhan response
            // msg.setReplyTo(InternetAddress.parse(to, false));
            // noi dung
            msg.setContent(bill, "text/HTML; charset=UTF-8");

            // gui mail
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            System.out.println("Gap loi trong qua trinh gui mail");
            e.printStackTrace();
            return false;
        }
    }

}
