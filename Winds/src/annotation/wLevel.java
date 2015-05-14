package annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface wLevel {
	static final String MODE_NORMAL="normal", MODE_BOSS="boss";
	static final String TYPE_BASIC="basic", TYPE_TOMODERATE="toModerate",
						TYPE_CUSTOM="custom", TYPE_MINE="mine";
	
	int idDB();
	String type();
	String mode();
	int idTheme();
	boolean uploaded();
}
