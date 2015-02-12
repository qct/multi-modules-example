package designPattern.create.factory.factoryMethod.putonggongchang;

/**
 * Created by alex on 2014/8/29.
 */
public class MailSender implements Sender{
    @Override
    public void send() {
        System.out.println("this is mailsender!");
    }
}
