package week07;

import org.apache.shardingsphere.infra.hint.HintManager;

import java.util.function.Supplier;

public class ShardingDateBaseRoute {

    private static final String ds0 = "ds-0";
    private static final String ds1 = "ds-1";

    public static <T> T sqlExecute(String user, Supplier<T> execute) {
        try (HintManager hintManager = HintManager.getInstance()) {
            String dbName;
            int mod = user.hashCode() % 2;
            if (mod == 0) {
                dbName = ds0;
            } else {
                dbName = ds1;
            }
            hintManager.setDatabaseShardingValue(dbName);
            return execute.get();
        }
    }

}