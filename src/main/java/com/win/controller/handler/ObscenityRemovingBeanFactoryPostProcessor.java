package com.win.controller.handler;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringValueResolver;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 17/9/2.
 */
public class ObscenityRemovingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private Set<String> obscenities;

    public ObscenityRemovingBeanFactoryPostProcessor() {
        this.obscenities = new HashSet<String>();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
            BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(new StringValueResolver() {
                @Override
                public String resolveStringValue(String strVal) {
                    if (isObscene(strVal))
                        return "******";
                    return strVal;
                }
            });
            visitor.visitBeanDefinition(bd);
        }

    }

    private boolean isObscene(String strVal) {
        return this.obscenities.contains(strVal.toString().toUpperCase());
    }
    public void setObscenities(Set<String> obscenities){
        if (this.obscenities==null){
            this.obscenities=new HashSet<String>();
        }
        this.obscenities.clear();
        for (String s : obscenities) {
            this.obscenities.add(s.toUpperCase());
        }
    }



    public static void main(String[] args) {
        ConfigurableListableBeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring/spring-root.xml"));
        BeanFactoryPostProcessor bfpp = (BeanFactoryPostProcessor)bf.getBean("bfpp");
        bfpp.postProcessBeanFactory(bf);
        System.out.println(bf.getBean("simpleBean"));

    }

}
