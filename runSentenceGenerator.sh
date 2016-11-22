#!/bin/bash
hadoop jar target/bespin-0.1.0-SNAPSHOT.jar io.bespin.java.mapreduce.bigram.BigramCount \
   -input input/brown.txt -output bigram-count
rm SentenceGenerator/HadoopOutput.txt
hdfs dfs -get bigram-count/part* SentenceGenerator/HadoopOutput.txt
cd SentenceGenerator
rm *.class
javac *.java
java driver
