package org.cdsdemo.shoppingcart.model.validatortests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.cdsdemo.shoppingcart.model.CartLineInfo;
import org.junit.Test;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/** 
 *         Simple test to make sure that Bean Validation is working
 *         (useful when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
public class CartLineInfoValidatorTest {

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    @Test
    public void orderQuantityTooLow() {

        LocaleContextHolder.setLocale(Locale.ENGLISH);
                
        CartLineInfo cartLineInfo = new CartLineInfo();
        cartLineInfo.setQuantity(0);
        
        Validator validator = createValidator();
        Set<ConstraintViolation<CartLineInfo>> constraintViolations = validator.validate(cartLineInfo);

        assertThat(constraintViolations.size()).isEqualTo(1);
        ConstraintViolation<CartLineInfo> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("quantity");
        assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 1");	
    }
}
