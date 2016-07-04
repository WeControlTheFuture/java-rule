package com.lenovo.sonar;

import java.util.Arrays;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;

import com.lenovo.sonar.rules.HybrisLayerCodingRule;

/**
 * Provide the "checks" (implementations of rules) classes that are gonna be
 * executed during source code analysis.
 *
 * This class is a batch extension by implementing the
 * {@link org.sonar.plugins.java.api.CheckRegistrar} interface.
 */
public class LenovoHybrisJavaFileCheckRegistrar implements CheckRegistrar {

	/**
	 * Register the classes that will be used to instantiate checks during
	 * analysis.
	 */
	@Override
	public void register(RegistrarContext registrarContext) {
		// Call to registerClassesForRepository to associate the classes with
		// the correct repository key
		registrarContext.registerClassesForRepository(LenovoHybrisJavaRulesDefinition.REPOSITORY_KEY, Arrays.asList(checkClasses()), Arrays.asList(testCheckClasses()));
	}

	/**
	 * Lists all the checks provided by the plugin
	 * 
	 * @return Class<? extends JavaCheck>[]
	 */
	public static Class<? extends JavaCheck>[] checkClasses() {
		return new Class[] { HybrisLayerCodingRule.class };
	}

	/**
	 * Lists all the test checks provided by the plugin
	 * 
	 * @return Class<? extends JavaCheck>[]
	 */
	public static Class<? extends JavaCheck>[] testCheckClasses() {
		return new Class[] {};
	}
}
