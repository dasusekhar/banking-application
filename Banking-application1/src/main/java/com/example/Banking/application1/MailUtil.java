package com.example.Banking.application1;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {
  @Autowired
  private JavaMailSender sender;

  public  String sendEmail(String to, String TextBody) throws MessagingException {
    String msg = "trigged";
    SimpleMailMessage message = new SimpleMailMessage();
    /*
    //this below  file send to the gamil
    // FileSystemResource fileSystemResource=new FileSystemResource(new File(attachment));
    MimeMessage mimeMailMessage=sender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMailMessage,true);
    //   mimeMessageHelper.setFrom("arjungautam8877@gmail.com");
    mimeMessageHelper.setTo(to);
    mimeMessageHelper.setText(TextBody);
    mimeMessageHelper.setSubject("MyBank");

    FileSystemResource fileSystemResource=
        new FileSystemResource(new File(attachment));
    mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),
        fileSystemResource);
    sender.send(mimeMailMessage);
    System.out.printf("Mail with attachment sent successfully..");



/*

     */
        try {

            message.setTo(to);
            message.setText(TextBody);
            message.setSubject("MyBank");
            message.setFrom("dasusekhar4130@gmail.com");


            sender.send(message);
            msg = "mail triggered successfully to : " + to;
        } catch (Exception e) {
            msg = e.getMessage();
        }
        //return msg;



    return msg;
  }

}
