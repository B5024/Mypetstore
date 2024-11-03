package csu.mypetstoree.web.servlet;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
//import jakarta.mail.Message.RecipientType;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;



public class MailcodeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String MAIL_CODE_FORM = "/WEB-INF/jsp/account/mailcode.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");

        if (!isValidEmail(email)){
            String Msg = "Invalid email address";
            request.setAttribute("mailcodeMsg", Msg);
            request.getRequestDispatcher(MAIL_CODE_FORM).forward(request, response);
        }else {
            String emailString = getNumbers(5);
            HttpSession session = request.getSession();
            session.setAttribute("email",email);
            session.setAttribute("emailcode",emailString);
            //创建Properties类用于记录邮箱的一些属性
            Properties props = new Properties();
            //表示SMTP发送邮件，必须进行身份验证
            props.put("mail.smtp.auth", "true");
            //此处填写SMTP服务器
            props.put("mail.smtp.host", "smtp.qq.com");
            //端口号
            props.put("mail.smtp.port","587");
            props.put("mail.smtp.starttls.enable", "true");
            //此处填写，写信人的账号
            props.put( "mail.user","2598882405@qq.com");
            //此处填写16位STMP口令
            props.put( "mail.password" ,"ljkjetivfrwkdjif");

            //构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    //用户名、密码
                    String userName = props.getProperty( "mail.user" );
                    String password = props.getProperty( "mail.password");
                    return new PasswordAuthentication(userName,password);
                }
            };
            //使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            //创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            try {
                //设置发件人
                InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
                message.setFrom(form);
                //设置收件人的邮箱
                InternetAddress to = new InternetAddress(email);
                message.setRecipient(RecipientType.TO, to);
                //设置邮件标题
                message.setSubject("My Pet Store 验证码");
                //设置邮件内容体
                message.setContent("验证码："+emailString,"text/html;charset = UTF-8");
                //发送邮件
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            if (session.getAttribute("isFindPassword").equals("true")) {
                response.sendRedirect("findPasswordForm");
            }else {
                response.sendRedirect("registerForm");
            }
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    public String getNumbers(int size) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String numString = "";
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            char c = str.charAt(r.nextInt(str.length()));
            numString = numString +c;
        }
        return numString;

    }
}
