package helper;

import model.GasStationEvent;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class AddNumOfLitres implements WindowFunction<GasStationEvent, Tuple3<Long, String, Double>, String, TimeWindow> {

    @Override
    public void apply(String fuelType, TimeWindow window, Iterable<GasStationEvent> gasStationEvents, Collector<Tuple3<Long, String, Double>> out) throws Exception {
        double sumOfLitres = 0;

        for (GasStationEvent event : gasStationEvents) {
            sumOfLitres += event.getNumOfLitres();
        }

        out.collect(new Tuple3<>(window.getEnd(), fuelType, sumOfLitres));
    }
}