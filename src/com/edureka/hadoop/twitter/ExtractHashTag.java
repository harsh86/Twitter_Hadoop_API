package com.edureka.hadoop.twitter;

import groovy.lang.Writable;

import java.io.IOException;

public class ExtractHashTag extends MapReduceBase implements Mapper {

	public void map(WritableComparable key, Writable values,
			OutputCollector output, Reporter reporter) throws IOException {
	}

}
