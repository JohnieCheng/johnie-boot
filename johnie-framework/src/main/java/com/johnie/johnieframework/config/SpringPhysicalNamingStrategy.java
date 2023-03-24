package com.johnie.johnieframework.config;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class SpringPhysicalNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {

  private static final String PREFIX = "t_";

  /** 为表添加指定前缀 */
  @Override
  public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
    return super.toPhysicalTableName(
        new Identifier(PREFIX + name.getText(), name.isQuoted()), jdbcEnvironment);
  }
}
