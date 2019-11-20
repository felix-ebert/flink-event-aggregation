import model.GasStationEvent;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.util.Random;

public class GasStationEventSourceFunction extends RichParallelSourceFunction<GasStationEvent> {

    private final int intervalMillis;
    private boolean running = true;

    public GasStationEventSourceFunction(int intervalMillis) {
        this.intervalMillis = intervalMillis;
    }

    @Override
    public void run(SourceContext<GasStationEvent> ctx) throws Exception {

        Random random = new Random();
        String fuelTypes[] = new String[]{"Diesel", "Super", "Super(E10)"};

        while (running) {
            String fuelType = fuelTypes[random.nextInt(fuelTypes.length)];
            double numOfLitres = random.nextDouble() * 150;
            long createdAt = System.currentTimeMillis();

            GasStationEvent event = new GasStationEvent(fuelType, numOfLitres, createdAt);

            // System.out.println(event);
            ctx.collect(event);

            Thread.sleep(intervalMillis);
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}
