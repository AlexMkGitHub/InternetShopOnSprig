package com.geekbrains.geekmarketwinter.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppLoggingAspect {
    @After("execution(public void com.geekbrains.geekmarketwinter.services.ShoppingCartService.addToCart(..))") // pointcut expression
    public void aopSimpleMethod() {
        System.out.println("добален товар в корзину");
    }

    //С помощью AOP cделать переcчет стоимости товара при добавлении товара в корзину
    @AfterReturning(
            pointcut = "execution(public * com.geekbrains.geekmarketwinter.services.ShoppingCartService.getCurrentCart(..))",
            returning = "result")
    public void afterShoppingCartInfo(JoinPoint joinPoint, ShoppingCart result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("В ShoppingCartService был вызван метод: " + methodSignature);
        System.out.println("Общая сумма заказа: " + result.getTotalCost());
    }

    //С помощью AOP cделать логирование для методов по работе с товарами
    @Before("execution(public * com.geekbrains.geekmarketwinter.services.ProductService.*(..))")
    public void beforeAnyMethodInProductServiceClassWithDetails(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("В ProductService был вызван метод: " + methodSignature);
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.out.println("Аргументы:");
            for (Object o : args) {
                System.out.println(o);
            }
        }
    }


}
