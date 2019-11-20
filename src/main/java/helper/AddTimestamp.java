package helper;

import model.GasStationEvent;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;

public class AddTimestamp extends AscendingTimestampExtractor<GasStationEvent> {

    @Override
    public long extractAscendingTimestamp(GasStationEvent gasStationEvent) {
        return gasStationEvent.getCreatedAt();
    }
}
