package helper;

import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

public class PrintSinkFunction implements SinkFunction<Tuple3<Long, String, Double>> {

    @Override
    public void invoke(Tuple3<Long, String, Double> value, Context context) {
        System.out.println(String.format("Timestamp: %d -- NumOfLitres: %.2f -- Fuel type: %s", value.f0, value.f2, value.f1));
    }
}
