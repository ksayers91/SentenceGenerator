Sentence Generator - Keenan Sayers
----------------------------------
(Uses and implements tools from https://github.com/lintool/bespin.git)

Github Repository:

Instructions and description:
- Open Terminal
- Sentence generator is currently being hosted on Cloudlab's cloud computing services, and a working infrastuctre is available via ssh at: 
	ssh -p 22 ksayers@ms0330.utah.cloudlab.us
	password:ksayers1
- Once logged in navigate to the following directory.
	- cd /local/hadoop/bespin
- The sentence generator has a large text file corpus pre-loaded onto its file system that is shared between nodes.
- Run the provided shell script: $ ./runSentenceGenerator.sh
- The cluster will now begin to parse through a 1GB text file called the Brown Corpus, counting all unigram and bigrams. This part is done in parallel, which allows the system to achieve significantly greater performance.
- After parsing the corpus, the program will move on to outputting all the counts to a text file. This text file is then used by a Java algorithm that evaluates probabilities of over possible word occurance and word occurance pair that was found in the corpus.
- Using this, sentences a generated using a weighted loterry selection system for word choices
- After all probabilites are generated, the system will prompt you to enter a word to begin the sentence. Enter any word, that you would belive to appear in the corpus. 
- Once entered it will a generate sentence
- Multiple sentences can be gnerated.
- Entering '//s' will stop the program.
- Output and input store the the HDFS (shared storage space) can be viewed at
	http://ms0330.utah.cloudlab.us:50070/
	Specifically output is here:http://ms0330.utah.cloudlab.us:50070/explorer.html#/user/ksayers/DEMO/OUTPUT
- https://github.com/lintool/bespin.git was used in the project as tools for parallell processing bigram counts on Apache Hadoop

Bugs:
- Input is not sanitised, as it was originally attended to accept files only. There will be certain input that causes errors.

Issues & limitations:
- Many features were still to be worked on. Including a better user interface and ability to pass whole files or file url locations.
- Only able to achieve about 50% paralellization. While still decent and more efficient, near full paralellization would have been nice and is achievable at some point with this specific implementation.
- Did work with Scala when setting up and configuring Apache Hadoop MapReduce, and well as Apache Apache Spark.
