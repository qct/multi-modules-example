package designPattern.create.factory.factoryMethod.multiFactoryMethod;

import designPattern.create.factory.factoryMethod.putonggongchang.Sender;

/**
 * Created by alex on 2014/8/29.
 */
public class FactoryTest {
    public static void main(String[] args) {
        SendFactory factory = new SendFactory();
        Sender sender = factory.produceMail();
        sender.send();
    }
}
