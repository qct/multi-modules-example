package designPattern.create.factory.factoryMethod.staticFactoryMethod;

import designPattern.create.factory.factoryMethod.putonggongchang.MailSender;
import designPattern.create.factory.factoryMethod.putonggongchang.Sender;
import designPattern.create.factory.factoryMethod.putonggongchang.SmsSender;

/**
 * Created by alex on 2014/8/29.
 */
public class SendFactory {
    public static Sender produceMail(){
        return new MailSender();
    }

    public static Sender produceSms(){
        return new SmsSender();
    }
}
