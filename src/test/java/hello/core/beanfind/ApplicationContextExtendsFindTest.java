package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextExtendsFindTest {
     AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

     @Test
     @DisplayName("부모타입으로 조회")
     void findBeanByParentTypeDuplicate() {
         Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));

     }
    @Test
    @DisplayName("부모타입과 이름 조회")
    void findBeanByParentTypeName() {
         DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }
    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanByChildType() {
        DiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모타입으로 모두 조회하기")
    void findAllBeanByParentType(){
         Map<String, DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
         assertThat(beans.size()).isEqualTo(2);
        for (String key : beans.keySet()) {
            System.out.println("key = " + key + " value = " + beans.get(key));
        }
    }

    @Test
    @DisplayName("부모타입으로 모두조회")
    void findALlBeanByObject() {
        Map<String, Object> beans = ac.getBeansOfType(Object.class);
        for (String key : beans.keySet()) {
            System.out.println("key = " + key + " value = " + beans.get(key));
        }
    }

    @Configuration
    static class TestConfig {
         @Bean
         public DiscountPolicy rateDiscountPolicy() {
             return new RateDiscountPolicy();
         }

         @Bean
         public DiscountPolicy fixDiscountPolicy() {
             return new FixDiscountPolicy();
         }
     }
}
