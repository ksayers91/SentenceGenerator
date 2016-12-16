#!/bin/bash
#Main Script that interacts with the user. Arguments -f and -u can be used to specify either a local file or file url
echo "$1"
echo "$2"
#Determine if argument passed is a text file
if [[ $2  == *.txt ]]; then
        case $1 in
                "-f")
                #Determine if the file location is valid
                        if [[ -f $2 ]]; then
                                echo "-----------------------------------"
                                echo "-----Using Local Text File: $2-----"
                                echo "-----------------------------------"
                                #copy data locally
                                cp $2 data/userDownload.txt
                        else
                                echo "$2 is not a valid file location"
                                exit
                        fi
                        ;;
                "-u") # URL argument specified
                        echo "-------------------------------------------------"
                        echo "-----Downloading text from specified url: $2-----"
                        echo "-------------------------------------------------"
                        # obtain File and copy locally
                        wget -O data/userDownload.txt $2
                        ;;
                *)
                        echo "Invalid parameter. Define input parameters '-f <file location> or -u <url>.'"
                        exit
                        ;;
        esac
else
        echo "Invalid Input. Must be .txt file."
        exit
#Mount file in Apache Hadoop HDFS
fi
echo "-----------------------------------------"
echo "-----Mounting file for use by Hadoop-----"
echo "-----------------------------------------"
hdfs dfs -rmr /user/$USER/input/userDownload.txt
hadoop fs -put data/userDownload.txt /user/$USER/input
echo "---------------------------"
echo "-----Training on input-----"
echo "---------------------------"
#Begin training with Apache Hadoop. Which will produce a bigram count list
hadoop jar target/bespin-0.1.0-SNAPSHOT.jar io.bespin.java.mapreduce.bigram.BigramCount \
   -input input/userDownload.txt -output bigram-count
rm SentenceGenerator/HadoopOutput.txt
echo "--------------------------------------------"
echo "-----Training complete, gathering files-----"
echo "--------------------------------------------"
hdfs dfs -get bigram-count/part* SentenceGenerator/HadoopOutput.txt
cd SentenceGenerator
rm *.class
#Run scripts for Sentence Generation Interface
echo "---------------------------------------------------------------------------"
echo "-----Building probabilities, and starting up Sentence Generator Script-----"
echo "---------------------------------------------------------------------------"
javac *.java
java driver

