/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipelinebasics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisComponent {

    String name();

    String hierarchy();

    String description() default "";

    String howtocite() default "";

}
