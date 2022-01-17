package pl.kamilwojcik.passwordkeeper.utils;

import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"/sql/drop.sql", "/sql/add_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public @interface ExecuteDML {
}
