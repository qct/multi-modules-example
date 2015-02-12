package designPattern.create.factory.factoryMethod.staticFactoryMethod;

import designPattern.create.factory.factoryMethod.putonggongchang.Sender;

/**
 * Created by alex on 2014/8/29.
 */
public class FactoryTest {
    public static void main(String[] args) {
        Sender sender = SendFactory.produceMail();
        sender.send();
    }
}
