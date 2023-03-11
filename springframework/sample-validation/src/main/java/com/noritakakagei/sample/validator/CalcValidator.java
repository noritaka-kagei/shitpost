package com.noritakakagei.sample.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.noritakakagei.sample.form.CalcForm;

@Component
public class CalcValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        // check whether argument Form class is validation target
        return CalcForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CalcForm form = (CalcForm) target;
        if (form.getLeftNum() != null && form.getRightNum() != null) {
            if (form.getLeftNum() == form.getRightNum()) {
                errors.reject("com.noritakakagei.sample.validator.CalcValidator.message");
            }
        }
    }
    
}