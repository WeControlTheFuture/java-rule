/*
 * Copyright (C) 2009-2014 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.lenovo.sonar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.plugins.java.Java;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

/**
 * Declare rule metadata in server repository of rules. 
 * That allows to list the rules in the page "Rules".
 */
public class LenovoHybrisJavaRulesDefinition implements RulesDefinition {

  public static final String REPOSITORY_KEY = "Li eCommerce Customize Rules";
  
  private static final String RESOURCE_BASE_PATH = "org/sonar/l10n/java/rules/java";
  
  private static final Set<String> RULE_TEMPLATES_KEY = Collections.emptySet();

  @Override
  public void define(Context context) {
    NewRepository repository = context.createRepository(REPOSITORY_KEY, Java.KEY);
    repository.setName(REPOSITORY_KEY + " Repository");

    RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(RESOURCE_BASE_PATH);

    ruleMetadataLoader.addRulesByAnnotatedClass(repository, new ArrayList<>(RulesList.getChecks()));

    setTemplates(repository);

    repository.done();
  }
  
  private static void setTemplates(NewRepository repository) {
      RULE_TEMPLATES_KEY.stream()
          .map(repository::rule)
          .filter(Objects::nonNull)
          .forEach(rule -> rule.setTemplate(true));
  }
}
