package cn.tangtj.clouddisk.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

/**
 * @author tang
 */
public class JdbcUtil {

    private static final DataSource DATA_SOURCE = new ComboPooledDataSource();

    public static QueryRunner getQueryRunner(){
        return new QueryRunner(DATA_SOURCE);
    }
}
