package com.lzj;

/**
 * @Description: TODO
 * @author: lzj
 * @date: 2021年04月27日 16:53
 */



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理举例
 *
 *
 * **/

interface Human{
    void getBelief();
    void eat(String food);
}
//被代理类
class SuperMan implements Human{

    @Override
    public void getBelief() {
        System.out.println("I believe I can fly!");
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃"+food);
    }
}


class ProxyFactory{
    //调用此方法，返回一个代理类的对象，解决了问题1
    public static Object getProxychInstance(Object obj){//obj:被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }
}
class MyInvocationHandler implements InvocationHandler {
    private Object obj;
    public void bind (Object obj){
        this.obj = obj;
    }

    //当我们通过代理类的对象，调用方法a，就会自动调用如下方法：invoke()
    //将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method：就是代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        if(method.getName().equals("eat")){
        System.out.println("before rent house");

        System.out.println("Method:" + method);

        // 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(obj, args);

        // 　　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after rent house");}
        return null;

    }

}
public class proxy {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        Human proxyInstance = (Human) ProxyFactory.getProxychInstance(superMan);
        proxyInstance.getBelief();
        proxyInstance.eat("麻辣烫");

    }
}