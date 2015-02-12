package designPattern.create.factory.factoryMethod.putonggongchang;

/**
 * Created by alex on 2014/8/29.
 */
public class FactoryTest {
    public static void main(String[] args) {
        SendFactory factory = new SendFactory();
        Sender sender = factory.produce("sms");
        sender.send();
    }
}
