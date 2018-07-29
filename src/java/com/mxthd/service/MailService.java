package com.mxthd.service;

import com.mxthd.bean.Mail;
import com.mxthd.bean.User;
import com.mxthd.dao.MailMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {
    @Autowired
    MailMapper mailMapper;
    @Autowired
    OptionsService optionsService;
    @Autowired
    JavaMailSender mailSender;

    private String FORMUSER = "229921717@qq.com";


    /**
     * 发送邮件
     * @param mail
     */
    private void addMail(Mail mail){
        mailMapper.addMail(mail);
    }
    //以下的方法为实际执行发送邮件

    /**
     * 新用户注册邮件
     * @param mail
     * @throws Exception
     */
    public void signup(Mail mail,String code) throws Exception {
        mail.setTitle(mail.getMail()+"注册确认");
        mail.setContext("signup.ftl");//设置文件模板
        Map<String,Object> data = getData();
        data.put("mail",mail.getMail());
        data.put("code",code);
        send(mail,data);//执行
    }
    public void updateIdByEmail(User user) throws Exception {
        //发送欢迎邮件
        Mail mail = new Mail();
        mail.setMail(user.getEmail());
        mail.setUid(user.getId());
        mail.setContext("newuser.ftl");//设置文件模板
        mail.setTitle(user.getUsername()+"您好,欢迎加入，请阅读以下内容。");
        Map<String,Object> data = getData();
        data.put("username",user.getUsername());
        send(mail,data);//执行
        //将注册邮件改为当前用户的邮件
        mailMapper.updateIdByEmail(user);
    }

    /**
     * 获取基本数据
     * @return
     */
    private Map<String,Object> getData(){
        Map<String,Object> data = new HashMap<>();
        data.put("title",optionsService.getByKey("title").getOptionValue());
        data.put("url",optionsService.getByKey("url").getOptionValue());
        return data;
    }

    /**
     * 执行方法
     * @param mail
     * @param data
     * @throws Exception
     */
    private void send(Mail mail,Map data) throws Exception {
        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 为防止乱码，添加编码集设置
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"UTF-8");
        //邮件基本信息
        message.setFrom(FORMUSER);//发送者.
        message.setTo(mail.getMail());//接收者.
        message.setSubject(mail.getTitle());//邮件主题.
        //**邮件内容，使用模板
        //模板中需要的数据
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        //设定去哪里读取相应的ftl模板
        cfg.setClassForTemplateLoading(this.getClass(),"/templates");
        //在模板文件目录中寻找名称为 XXX.ftl 的模板文件
        Template template = cfg.getTemplate(mail.getContext(),"UTF-8");
        mail.setContext(FreeMarkerTemplateUtils.processTemplateIntoString(template, data));
        message.setText(mail.getContext(),true);
        mailSender.send(mimeMessage);//发送邮件
        addMail(mail);//数据库记录
    }
}
