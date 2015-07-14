package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)


/**
 * Annotation used in Winds theme archives.
 */
public @interface wFiles {
	String music();
	String logo();
	String background();
	String interactions();
	String sprites64();
	String sprites128();
	String spritesCollectable();
}
