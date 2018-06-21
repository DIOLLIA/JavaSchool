package schedule.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class MyValidator {

    public void validate(Object object, javax.validation.Validator validator) {
        Set<ConstraintViolation<Object>> constraintViolations = validator
                .validate(object);

        System.out.println(object);
        System.out.println(String.format("fields with warnings: %d",
                constraintViolations.size()));

        for (ConstraintViolation<Object> cv : constraintViolations)
            System.out.println(String.format(
                    "Warning, error! property: [%s], value: [%s], message: [%s]",
                    cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
    }
}
