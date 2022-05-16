package week01;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        // 创建类加载器
        ClassLoader classLoader = new MyClassLoader();
        // 使用重写类加载器进行加载得到类对象
        Class<?> clazz = classLoader.loadClass("Hello");
        // 打印该类所有自定义的方法
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println(clazz.getSimpleName() + "." + m.getName());
        }
        // 创建对象
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 指定方法名调用实例方法
        Method method = clazz.getMethod("hello");
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (null == name || name.isEmpty()) {
            return null;
        }
        // 读取文件名
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name + ".xlass");
        try {
            // 以该文件流长度创建字节数组大小
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            // 转换
            byte[] classBytes = decode(byteArray);
            // 通知底层定义这个类
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            close(inputStream);
        }
    }

    // 解码
    private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            // 字节码反码处理
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

    // 关闭
    private static void close(Closeable res) {
        if (null != res) {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
