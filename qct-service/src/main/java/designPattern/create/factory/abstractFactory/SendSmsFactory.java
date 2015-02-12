package designPattern.create.factory.abstractFactory;

import designPattern.create.factory.factoryMethod.putonggongchang.Sender;
import designPattern.create.factory.factoryMethod.putonggongchang.SmsSender;

/**
 * Created by alex on 2014/8/29.
 */
public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
