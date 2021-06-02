/*
 * Copyright (C) 2009-2014 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.lenovo.sonar;

import org.sonar.api.Plugin;


/**
 * Entry point of plugin
 */
public class LenovoHybrisJavaRulesPlugin implements Plugin {

@Override
public void define(Context context) {
	// TODO Auto-generated method stub
	context.addExtension(LenovoHybrisJavaRulesDefinition.class);
	context.addExtension(LenovoHybrisJavaFileCheckRegistrar.class);
}
}
