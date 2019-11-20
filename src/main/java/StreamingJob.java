import helper.AddNumOfLitres;
import helper.AddTimestamp;
import helper.PrintSinkFunction;
import model.GasStationEvent;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;


public class StreamingJob {

    public static void main(String[] args) throws Exception {

        // set up streaming execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        // start the sensor event generator
        DataStreamSource<GasStationEvent> gasStationEventDataStreamSource = env.addSource(new GasStationEventSourceFunction(100));

        // compute number of litres per minute for each fuel type
        DataStream<Tuple3<Long, String, Double>> numOfLitresEveryMinute = gasStationEventDataStreamSource
                .assignTimestampsAndWatermarks(new AddTimestamp())
                .keyBy((GasStationEvent event) -> event.getFuelType())
                .timeWindow(Time.seconds(5))
                .apply(new AddNumOfLitres());

        // find the most sold fuel type in each time window
        DataStream<Tuple3<Long, String, Double>> maxEveryMinute = numOfLitresEveryMinute
                .timeWindowAll(Time.seconds(5))
                .maxBy(2);

        maxEveryMinute.addSink(new PrintSinkFunction());

        // execute program
        env.execute("Flink Event Aggregation");
    }
}