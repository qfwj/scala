package javatest.spi;

import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.ServiceLoader;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/10/30 15:55
 */
public class TestSpi {
    public static void main(String[] args) throws Exception {
        HashMap map = new HashMap();
        StringUtils.isEmpty("");
        //   Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://10.50.162.178:56407/operation-system?" +
                "useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull", "admin", "yCRTqH3zOMPRm9clijr5");

        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);

        for (IShout s : shouts) {
            s.shout();
        }

    }
}
