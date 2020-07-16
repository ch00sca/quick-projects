package com.helvetia.heap.jsf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.inject.Stereotype;
import javax.enterprise.util.Nonbinding;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface HeapController {
    @Nonbinding
    String viewId() default "";
}
