package com.gdack.hadoop.v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class CitationTemplate {

  public static class MapClass extends Mapper<Text, Text, Text, Text> {

    @Override
    protected void map(Text key, Text value, Context context)
        throws IOException, InterruptedException {
      super.map(key, value, context);
    }
  }

  public static class Reduce extends Reducer<Text, Text, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
        throws IOException, InterruptedException {
      // Sort values in alphabetical order.
      List<String> valueList = new ArrayList<>();
      for (Text value : values) {
        valueList.add(value.toString());
      }
      Collections.sort(valueList);

      StringBuilder csv = new StringBuilder();
      for (String value : valueList) {
        if (csv.length() > 0) {
          csv.append(",");
        }
        csv.append(value);
      }

      context.write(key, new Text(csv.toString()));
    }
  }

  public static void main(String[] args)
      throws IOException, ClassNotFoundException, InterruptedException {
    Configuration conf = new Configuration();
    conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", ",");
    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();

    if (otherArgs.length < 2) {
      System.err.println("Usage: citation <in> [<in>...] <out>");
      System.exit(2);
    }

    Job job = Job.getInstance(conf, "citation");
    job.setJarByClass(CitationTemplate.class);
    job.setMapperClass(MapClass.class);
    job.setCombinerClass(Reduce.class);
    job.setReducerClass(Reduce.class);
    job.setInputFormatClass(KeyValueTextInputFormat.class);
    job.setOutputValueClass(TextOutputFormat.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    for (int i = 0; i < otherArgs.length - 1; i++) {
      FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
    }
    FileOutputFormat.setOutputPath(job, new Path(otherArgs[otherArgs.length - 1]));

    System.exit(job.waitForCompletion(true)? 0 : 1);
  }
}
