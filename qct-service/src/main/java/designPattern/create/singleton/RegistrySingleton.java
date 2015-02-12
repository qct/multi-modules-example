package designPattern.create.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 2014/8/30.
 */
public class RegistrySingleton {
    /**
     * 充当了Bean实例的缓存，实现方式和单例注册表相同
     */
    private static Map<String, Object> registry = new HashMap();

    //受保护的默认构造函数，如果为继承关系，则可以调用，克服了单例类不能为继承的缺点
    protected RegistrySingleton() {}

    static {
        RegistrySingleton rs = new RegistrySingleton();
        registry.put(rs.getClass().getName(), rs);
    }

    //静态工厂方法，返回此类的唯一实例
    public static RegistrySingleton getInstance(String name) {
        if(name == null) {
            name = "RegistrySingleton";
        }
        if(registry.get(name) == null) {
            try {
                registry.put(name, Class.forName(name).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return (RegistrySingleton) registry.get(name);
    }
}
