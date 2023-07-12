package com.rain.study;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;


public class FlinkStreamJavaExample {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        // 读取文本路径信息，并用逗号隔开
        final String[] filePaths = ParameterTool.fromArgs(args).get("filePath", "/opt/log1.txt,/opt/log2.txt").split(",");

        assert filePaths.length > 0;

        // WindowTime是设置窗口时间大小，默认情况下2分钟一个从窗口足够读取文本内容的所有信息
        final int windowTime = ParameterTool.fromArgs(args).getInt("windowTime", 2);

        // 构建运行环境，使用Environment处理窗口数据
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.setParallelism(1); // 并行度

        // 读取文本的数据流
        DataStream<String> unionStream = env.readTextFile(filePaths[0]); // 初始值
        if(filePaths.length > 1){
            for (int i = 1; i < filePaths.length; i++){
                unionStream = unionStream.union(env.readTextFile(filePaths[i]));
            }
        }

        // 数据转换，构造整个数据处理的逻辑，计算出结果并打印出来
        unionStream.map(new MapFunction<String, UserRecord>() {

            @Override
            public UserRecord map(String value) throws Exception {
                // TODO Auto-generated method stub
                return getRecord(value);
            }
        })
                .assignTimestampsAndWatermarks(new Record2TimeStampsExecuter()) // 判断新水印产生时间是否大于上一次水印产生时间，如果大于选择新水印，小于则选择上一次水印
                .filter(new FilterFunction<UserRecord>() {

                    @Override
                    public boolean filter(UserRecord value) throws Exception {
                        // TODO Auto-generated method stub
                        return value.gender.equals("female");
                    }
                })
                .keyBy(new UserRecordSelector()) // 将<<name,gender>,shoppingTime>
                .window(TumblingEventTimeWindows.of(Time.milliseconds(windowTime)))
                .reduce(new ReduceFunction<UserRecord>() {

                    @Override
                    public UserRecord reduce(UserRecord value1, UserRecord value2) {
                        // TODO Auto-generated method stub
                        value1.shoppingTime += value2.shoppingTime;
                        return value1;
                    }
                })
                .filter(new FilterFunction<UserRecord>() {

                    @Override
                    public boolean filter(UserRecord value) {
                        // TODO Auto-generated method stub
                        return value.shoppingTime > 120;
                    }

                })
                .print()
        ;

        env.execute("FlinkStreamJavaExample");

    }

    // 构建KeyBy的关键字作为分组依据
    public static class UserRecordSelector implements KeySelector<UserRecord, Tuple2<String, String>> {

        @Override
        public Tuple2<String, String> getKey(UserRecord value) throws Exception {
            // TODO Auto-generated method stub
            return Tuple2.of(value.name, value.gender);
        }

    }

    // 定义水印方法
    public static class Record2TimeStampsExecuter implements AssignerWithPunctuatedWatermarks<UserRecord> {

        @Override
        public long extractTimestamp(UserRecord arg0, long arg1) {
            // TODO Auto-generated method stub
            return System.currentTimeMillis();
        }

        @Override
        public Watermark checkAndGetNextWatermark(UserRecord element, long extractedTimestamp) {
            // TODO Auto-generated method stub
            return new Watermark(extractedTimestamp - 1);
        }

    }

    // 解析文本数据，构造UserRecord数据结构
    public static UserRecord getRecord(String line){
        String[] elems = line.split(",");
        assert elems.length == 3;
        return new UserRecord(elems[0], elems[1], Integer.parseInt(elems[2]));
    }

    // 定义UserRecord数据结构的定义，并重写toString函数
    public static class UserRecord{
        public String name;
        public String gender;
        public int shoppingTime;
        public UserRecord(){};
        public UserRecord(String n, String g, int s){
            this.name = n;
            this.gender = g;
            this.shoppingTime = s;
        }

        public String toString(){
            return "name: " + name + ", gender: " + gender + ", shoppingTime: " + shoppingTime;
        }
    } 
}
