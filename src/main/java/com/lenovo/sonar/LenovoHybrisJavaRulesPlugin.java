/*
 * Copyright (C) 2009-2014 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.lenovo.sonar;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;

/**
 * Entry point of plugin
 */
public class LenovoHybrisJavaRulesPlugin extends SonarPlugin {

  @Override
  public List getExtensions() {
    return Arrays.asList(
      // server extensions -> objects are instantiated during server startup
      LenovoHybrisJavaRulesDefinition.class,

      // batch extensions -> objects are instantiated during code analysis
      LenovoHybrisJavaFileCheckRegistrar.class);
  }
}
