package annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface wCard {
	String creator();
	String date();
	String name();
	String description();
}