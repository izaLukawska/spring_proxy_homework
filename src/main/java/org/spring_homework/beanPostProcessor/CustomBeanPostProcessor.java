package org.spring_homework.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Optional;

public class CustomBeanPostProcessor implements BeanPostProcessor {

	private final ApplicationContext applicationContext;

	public CustomBeanPostProcessor(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Before init method");
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Object myContextBean = applicationContext.getBean(beanName);
		Class<?> myBeanClass = myContextBean.getClass();
		Optional<Method> transactionMethod = Arrays.stream(myBeanClass.getDeclaredMethods())
				.filter(method -> method.isAnnotationPresent(Transaction.class)).findFirst();
		return transactionMethod.map(myCustomMethod -> Proxy.newProxyInstance
				(myBeanClass.getClassLoader(), myBeanClass.getInterfaces(), (proxy, method, args)->{
					if (method.getName().equals(myCustomMethod.getName())) {
						System.out.println("Transaction started: " + myCustomMethod
								.getAnnotation(Transaction.class)
								.methodName());
						Object invoke = method.invoke(myContextBean, args);
						System.out.println("Transaction finished");
						return invoke;
					}
					return method.invoke(myContextBean, args);
				})).orElse(myBeanClass);
	}
}
