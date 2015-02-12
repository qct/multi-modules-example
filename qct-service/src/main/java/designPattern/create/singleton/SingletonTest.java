package designPattern.create.singleton;

/**
 * Created by alex on 2014/8/30.
 */
public class SingletonTest {
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();

        RegistrySingleton rs = RegistrySingleton.getInstance("designPattern.create.singleton.RegistrySingleton");

        RegistrySingleton rsExtend = RegistrySingletonExtend.getInstance("designPattern.create.singleton.RegistrySingletonExtend");

        System.out.println(123);
    }
}
