package designPattern.create.factory.abstractFactory;

import designPattern.create.factory.factoryMethod.putonggongchang.Sender;

/**
 * Created by alex on 2014/8/29.
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.send();
    }
}
