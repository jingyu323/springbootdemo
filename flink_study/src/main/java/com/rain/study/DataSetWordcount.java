package com.rain.study;


import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class DataSetWordcount {
    public static void main(String[] args) throws Exception {
        // 1、创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 2、读取数据
        String path = "e:\\study\\flink_study\\src\\main\\resources\\wordcount.txt";
        // DataSet -> Operator -> DataSource
        DataSet<String> inputDataSet = env.readTextFile(path);

        // 3、扁平化 + 分组 + sum
        DataSet<Tuple2<String, Integer>> resultDataSet = inputDataSet.flatMap(new MyFlatMapFunction())
                .groupBy(0) // (word, 1) -> 0 表示 word
                .sum(1);

        resultDataSet.print();
    }

    public static class MyFlatMapFunction implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String input, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String[] words = input.split(" ");
            for (String word : words) {
                collector.collect(new Tuple2<>(word, 1));
            }
        }
    }
}
