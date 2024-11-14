package com.rain.study;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class StreamWordcount {
    private static final String[] WORDS = new String[]{
            "To be, or not to be,--that is the question:--",
            "Whether 'tis nobler in the mind to suffer"
    };
    public static void main(String[] args) throws Exception {
        // 1、创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();



        // 2、读取 socket 数据
        DataStreamSource<String> inputDataStream = env.socketTextStream("192.168.200.102", 9999);

        // 3、计算
        SingleOutputStreamOperator<Tuple2<String, Integer>> resultDataStream = inputDataStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String input, Collector<Tuple2<String, Integer>> collector) throws Exception {
                        String[] words = input.split(" ");
                        for (String word : words) {
                            collector.collect(new Tuple2<>(word, 1));
                        }
                    }
                }).keyBy(0)
                .sum(1);

        // 4、输出
        resultDataStream.print();

        // 5、启动 env
        env.execute();
    }
}
