package com.example.aboutwork;

import com.example.aboutwork.callBack.MyCallBack;
import com.example.aboutwork.callBack.ProcessorInterface;
import com.example.aboutwork.callBack.UseMyProcessor;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class TestMyProcessor {

    public final String CLASSES = "classes";
    public final String _CLASS = ".class";

    List<ProcessorInterface> callBacks = Lists.newArrayList();
    ConcurrentMap<String, Object> callBackMap = Maps.newConcurrentMap();
    ConcurrentMap<String, Class<?>> callBackTypeMap = Maps.newConcurrentMap();

    @SneakyThrows
    @Test
    public void 测试回调() {
        //回调前处理
        Object obj = callBackMap.get("UseMyProcessor");
        if (Objects.nonNull(obj)) {
            UseMyProcessor useMyProcessor = (UseMyProcessor) obj;
            String typeName = useMyProcessor.getClass().getTypeName();
            for (Map.Entry<String, Class<?>> entry : callBackTypeMap.entrySet()) {
                String key = entry.getKey();
                if (key.equals(typeName)) {
                    Class aClass = entry.getValue();
                    Optional<ProcessorInterface> first = callBacks.stream().filter(a -> a.getClass().equals(aClass)).findFirst();
                    if (first.isPresent()) {
                        useMyProcessor = (UseMyProcessor) first.get().beforeCall(useMyProcessor);
                    }
                    useMyProcessor.use();
                    if (first.isPresent()) {
                        useMyProcessor = (UseMyProcessor) first.get().afterCall(useMyProcessor);
                    }
                }
            }

            System.err.println(useMyProcessor);

        }

    }

    @Before
    public <T> void prepareData() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
        URL url = defaultClassLoader != null ? defaultClassLoader.getResource("com/example/aboutwork/callBack/") : null;
        if (url != null) {
            File file = new File(url.getPath());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    String classPath = f.getAbsolutePath().replace("\\", ".");
                    classPath = classPath.substring(classPath.indexOf(CLASSES) + 1 + CLASSES.length(), classPath.lastIndexOf(_CLASS));
                    Class<?> aClass = defaultClassLoader.loadClass(classPath);
                    // interface com.example.aboutwork.callBack.CallBackInterface
                    // interface com.example.aboutwork.callBack.MyCallBack
                    // class com.example.aboutwork.callBack.MyCallBackImpl
                    System.err.println(aClass);
                    Constructor<?>[] constructors = aClass.getConstructors();
                    if (constructors.length == 0) {
                        continue;
                    }
                    Object obj = aClass.getConstructor().newInstance();
                    if (ProcessorInterface.class.isAssignableFrom(aClass)) {
                        Type[] genericInterfaces = aClass.getGenericInterfaces();
                        List<Type> collect = Arrays.asList(genericInterfaces).stream().map(a -> ((ParameterizedType) a))
                                .map(ParameterizedType::getActualTypeArguments)
                                .map(Arrays::asList)
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList());
                        callBacks.add(((ProcessorInterface) obj));
                        callBackTypeMap.put(collect.get(0).getTypeName(), aClass);
                    }
                    if (aClass.isAnnotationPresent(MyCallBack.class)) {
                        callBackMap.put(aClass.getSimpleName(), obj);
                    }
                }
            }
        }
    }

}
