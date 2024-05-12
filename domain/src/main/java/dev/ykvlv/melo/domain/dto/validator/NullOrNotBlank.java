package dev.ykvlv.melo.domain.dto.validator;

import jakarta.validation.*;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullOrNotBlank.Validator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface NullOrNotBlank {
    String message() default "{jakarta.validation.constraints.NullOrNotBlank.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<NullOrNotBlank, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return value == null || !value.trim().isEmpty();
        }
    }

}
