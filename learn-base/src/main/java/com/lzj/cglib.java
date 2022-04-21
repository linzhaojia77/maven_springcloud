package com.lzj;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * @Description: TODO
 * @author: lzj
 * @date: 2021年04月28日 9:32
 */
//目标类
class CglibDao {

    public CglibDao() {
        select();
    }

    public void update() {
        System.out.println("CglibDao.update()");
    }

    public void select() {
        System.out.println("CglibDao.select()");
    }

}
//拦截器1
class CglibDaoProxy implements MethodInterceptor {

    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method invoke");
        methodProxy.invokeSuper(object, args);
        System.out.println("after method invoke");
        return object;
    }

}
//拦截器2

class CglibDaoTwoProxy implements MethodInterceptor {
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("startTime : " + Calendar.getInstance().getTimeInMillis());
        methodProxy.invokeSuper(object, args);
        System.out.println("endTime : " + Calendar.getInstance().getTimeInMillis());
        return object;
    }
}
//回调器

class CglibFilter implements CallbackFilter {
    public int accept(Method method) {
        if ("select".equals(method.getName())) {
            return 1;
        }
        return 0;
    }

}

public class cglib {
    public static void main(String[] args) {
        cglibTest2();
    }


    public static void cglibTest2() {
        CglibDaoTwoProxy twoProxy = new CglibDaoTwoProxy();
        CglibDaoProxy proxy = new CglibDaoProxy();

        CglibFilter filter = new CglibFilter();
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(CglibDao.class);
        //设置拦截器替代调用方法
        enhancer.setCallbacks(new Callback[]{proxy, twoProxy});
        //配置回调器，在配置多拦截器时才会用到，根据返回的int作为拦截器数组的下标，采用不同的拦截器
        enhancer.setCallbackFilter(filter);
        //生成代理类
        CglibDao dao = (CglibDao) enhancer.create();
        dao.update();
        dao.select();
    }

}