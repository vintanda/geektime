package week07;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

public class SpecifiedDataBaseSharding implements HintShardingAlgorithm<String> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String dbName : availableTargetNames) {
            for (String value : shardingValue.getValues()) {
                if (StringUtils.equals(dbName, value)) {
                    result.add(dbName);
                }
            }
        }
        return result;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return null;
    }
}
