import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/8/30 10:27
 */
public class TestSourceFunction<T> implements SourceFunction<T>, CheckpointedFunction {
    private Integer count = 0;
    private volatile boolean isRunning = true;

    private transient ListState<Integer> checkpointedCount;

    @Override
    public void run(SourceContext ctx) {
        while (isRunning && count < 1000) {
            // this synchronized block ensures that state checkpointing,
            // internal state updates and emission of elements are an atomic operation
            synchronized (ctx.getCheckpointLock()) {
                ctx.collect(count);
                count++;
            }
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    @Override
    public void initializeState(FunctionInitializationContext context) throws Exception{
        this.checkpointedCount = context
                .getOperatorStateStore()
                .getListState(new ListStateDescriptor<>("count", Integer.class));

        if (context.isRestored()) {
            for (Integer count : this.checkpointedCount.get()) {
                this.count = count;
            }
        }
    }


    @Override
    public void snapshotState(FunctionSnapshotContext context) throws Exception{
        this.checkpointedCount.clear();
        this.checkpointedCount.add(count);
    }
}

