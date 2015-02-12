package designPattern.create.factory.factoryMethod.multiFactoryMethod;

import designPattern.create.factory.factoryMethod.putonggongchang.MailSender;
import designPattern.create.factory.factoryMethod.putonggongchang.Sender;
import designPattern.create.factory.factoryMethod.putonggongchang.SmsSender;

/**
 * Created by alex on 2014/8/29.
 */
public class SendFactory {
    public Sender produceMail(){
        return new MailSender();
    }
    public Sender produceSms(){
        return new SmsSender();
    }
}
