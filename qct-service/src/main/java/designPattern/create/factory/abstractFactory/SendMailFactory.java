package designPattern.create.factory.abstractFactory;

import designPattern.create.factory.factoryMethod.putonggongchang.MailSender;
import designPattern.create.factory.factoryMethod.putonggongchang.Sender;

/**
 * Created by alex on 2014/8/29.
 */
public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
