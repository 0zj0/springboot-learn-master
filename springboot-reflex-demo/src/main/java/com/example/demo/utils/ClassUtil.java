package com.example.demo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangjie
 * @date 2019/5/7 16:20
 */
public class ClassUtil {

    /**
     * 执行指定对象的指定方法
     * @param obj 方法执行的那个对象.
     * @param methodName 类的一个方法的方法名. 该方法也可能是私有方法.
     * @param args 调用该方法需要传入的参数
     * @return java.lang.Object
     * @author 张杰
     * @date 2019/05/07 16:21
     */
    public static Object invoke(Object obj, String methodName, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        //1.获取参数对应class
        Class[] params = new Class[args.length];
        for(int i=0 ; i < args.length; i++){
            params[i] = args[i].getClass();
        }

        //2.获取需要执行的方法
        //如果使用getDeclaredMethod，就不能获取父类方法，如果使用getMethod，就不能获取私有方法
        Class clazz = obj.getClass();
        Method method = getMethod(clazz,methodName,params);
        if(method == null){
            System.out.println("方法不存在");
            return null;
        }
        //私有方法
        //method.setAccessible(true);
        //3.执行method方法
        return method.invoke(obj,args);
    }

    /**
     * 执行指定对象名称的指定方法
     * @param className 方法执行的那个对象名称（路径）.
     * @param methodName 类的一个方法的方法名. 该方法也可能是私有方法.
     * @param args 调用该方法需要传入的参数
     * @return java.lang.Object
     * @author 张杰
     * @date 2019/05/07 16:21
     */
    public static Object invoke(String className, String methodName, Object... args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object obj = Class.forName(className).newInstance();
        return invoke(obj,methodName,args);
    }

    /**
     * 获取指定class的指定方法（含父类方法）
     * @param clazz class类
     * @param methodName  类的一个方法的方法名. 该方法也可能是私有方法.
     * @param params 调用该方法需要传入的参数class数组
     * @return java.lang.reflect.Method
     * @author 张杰
     * @date 2019/05/07 18:26
     */
    public static Method getMethod(Class clazz, String methodName, Class... params){
        while (clazz != Object.class){
            try {
                Method method = clazz.getDeclaredMethod(methodName,params);
                return method;
            } catch (NoSuchMethodException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

    /**
     * 获取指定object的指定列的value
     * @param obj object对象
     * @param field 列
     * @return java.lang.Object
     * @author 张杰
     * @date 2019/05/07 18:33
     */
    public static Object getFieldValue(Object obj, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(obj);
    }

    /**
     * 为指定obj的指定列赋值value
     * @param obj object对象
     * @param field 列
     * @param value 值
     * @return java.lang.Object
     * @author 张杰
     * @date 2019/05/07 18:33
     */
    public static void setFieldValue(Object obj, Field field, Object value) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(obj,value);
    }

    /**
     * 获取指定class的指定列（含父类列字段）
     * @param clazz class类
     * @param fieldName 列名
     * @return java.lang.reflect.Field
     * @author 张杰
     * @date 2019/05/07 18:35
     */
    public static Field getField(Class clazz, String fieldName){
        while (clazz != Object.class){
            try {
                Field field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

}
