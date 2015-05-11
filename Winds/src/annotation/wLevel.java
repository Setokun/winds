package annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface wLevel {
	int idDB();
	String type();
	String mode();
	int idTheme();
	boolean uploaded();
}
