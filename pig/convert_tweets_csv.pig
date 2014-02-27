register '/Users/harsh86/.m2/repository/com/twitter/elephantbird/elephant-bird-core/4.3/elephant-bird-core-4.3.jar';
register '/Users/harsh86/.m2/repository/com/twitter/elephantbird/elephant-bird-hadoop-compat/4.3/elephant-bird-hadoop-compat-4.3.jar';
register '/Users/harsh86/.m2/repository/com/twitter/elephantbird/elephant-bird-pig/4.3/elephant-bird-pig-4.3.jar';
register '/Users/harsh86/.m2/repository/com/googlecode/json-simple/json-simple/1.1/json-simple-1.1.jar';
register '/Users/harsh86/.m2/repository/com/google/guava/guava/r06/guava-r06.jar'

rawtweets = load '/usr/flume/tweets/2013/11/29/15/FlumeData.1385762054102.tmp' using com.twitter.elephantbird.pig.load.JsonLoader();

tweetsforprcsng = limit rawtweets 100; 

tweetstuple = foreach tweetsforprcsng generate (chararray)$0#'text' as text,(long)$0#'id' as id,(chararray)$0#'source' as source, com.twitter.elephantbird.pig.piggybank.JsonStringToMap($0#'user') as user; 

tweets = foreach tweetstuple generate org.apache.pig.builtin.StringConcat(text,';',id,';',(chararray)user#'screen_name',';',source);

 STORE tweets INTO '/tmp/output/tweets/blackfriday';
-- dump tweets;
