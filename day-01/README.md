# Day 01 简单尝试 Hadoop

从Hadoop官网上下载软件安装包，解压放置到自定义的位置，跳转到安装分目录配置`etc/hadoop/hadoop-env.sh`文件中的`JAVA_HOME`环境变量.

## 简单了解Hadoop命令行信息.

执行`bin\hadoop`命令，查看这个命令相关的信息.

```bash
Usage: hadoop [OPTIONS] SUBCOMMAND [SUBCOMMAND OPTIONS]
 or    hadoop [OPTIONS] CLASSNAME [CLASSNAME OPTIONS]
  where CLASSNAME is a user-provided Java class

  OPTIONS is none or any of:

--config dir                     Hadoop config directory
--debug                          turn on shell script debug mode
--help                           usage information
buildpaths                       attempt to add class files from build tree
hostnames list[,of,host,names]   hosts to use in slave mode
hosts filename                   list of hosts to use in slave mode
loglevel level                   set the log4j level for this command
workers                          turn on worker mode

  SUBCOMMAND is one of:


    Admin Commands:

daemonlog     get/set the log level for each daemon

    Client Commands:

archive       create a Hadoop archive
checknative   check native Hadoop and compression libraries availability
classpath     prints the class path needed to get the Hadoop jar and the required libraries
conftest      validate configuration XML files
credential    interact with credential providers
distch        distributed metadata changer
distcp        copy file or directories recursively
dtutil        operations related to delegation tokens
envvars       display computed Hadoop environment variables
fs            run a generic filesystem user client
gridmix       submit a mix of synthetic job, modeling a profiled from production load
jar <jar>     run a jar file. NOTE: please use "yarn jar" to launch YARN applications, not this
              command.
jnipath       prints the java.library.path
kdiag         Diagnose Kerberos Problems
kerbname      show auth_to_local principal conversion
key           manage keys via the KeyProvider
rumenfolder   scale a rumen input trace
rumentrace    convert logs into a rumen trace
s3guard       manage metadata on S3
trace         view and modify Hadoop tracing settings
version       print the version

    Daemon Commands:

kms           run KMS, the Key Management Server

SUBCOMMAND may print help when invoked w/o parameters or with -h.
```

这个命令给出了Hadoop相关的操作命令，可以通过使用命令来完成Hadoop的基本操作.

## 小试一下

Hadoop在`share/hadoop/mapreduce`目录提供了名为`hadoop-mapreduce-examples-3.1.1.jar`的测试程序.

```bash
$ bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.1.jar
  An example program must be given as the first argument.
  Valid program names are:
    aggregatewordcount: An Aggregate based map/reduce program that counts the words in the input files.
    aggregatewordhist: An Aggregate based map/reduce program that computes the histogram of the words in the input files.
    bbp: A map/reduce program that uses Bailey-Borwein-Plouffe to compute exact digits of Pi.
    dbcount: An example job that count the pageview counts from a database.
    distbbp: A map/reduce program that uses a BBP-type formula to compute exact bits of Pi.
    grep: A map/reduce program that counts the matches of a regex in the input.
    join: A job that effects a join over sorted, equally partitioned datasets
    multifilewc: A job that counts words from several files.
    pentomino: A map/reduce tile laying program to find solutions to pentomino problems.
    pi: A map/reduce program that estimates Pi using a quasi-Monte Carlo method.
    randomtextwriter: A map/reduce program that writes 10GB of random textual data per node.
    randomwriter: A map/reduce program that writes 10GB of random data per node.
    secondarysort: An example defining a secondary sort to the reduce.
    sort: A map/reduce program that sorts the data written by the random writer.
    sudoku: A sudoku solver.
    teragen: Generate data for the terasort
    terasort: Run the terasort
    teravalidate: Checking results of terasort
    wordcount: A map/reduce program that counts the words in the input files.
    wordmean: A map/reduce program that counts the average length of the words in the input files.
    wordmedian: A map/reduce program that counts the median length of the words in the input files.
    wordstandarddeviation: A map/reduce program that counts the standard deviation of the length of the words in the input files.
```

通过执行上述命令，可以查看到Hadoop测试程序提供了哪些小Demo，选择一个小Demo来完成Hadoop的简单试用吧. 以`wordcount`为例，执行结果如下：

```bash
$ bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.1.jar wordcount -h
  Usage: wordcount <in> [<in>...] <out>
```

可以看到程序需要我们提供输入目录和输出目录. 通过指定输入目录和输出目录来完成上述程序的运行.

```bash
$ bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.1.jar wordcount input output
$ cat output/*
```

经过一系列的日志输出之后，我们可以看到`wordcount`程序的运行结果了.
