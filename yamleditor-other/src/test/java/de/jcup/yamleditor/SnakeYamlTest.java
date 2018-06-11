/*
 * Copyright 2018 Albert Tregnaghi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 */
package de.jcup.yamleditor;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.error.MarkedYAMLException;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.scanner.ScannerException;

public class SnakeYamlTest {

	@Test
	public void test1() {
		Yaml yaml = new Yaml();
		String document = "hello: 25\ngargamel: true";
		Map map = yaml.load(document);
//		assertEquals("{hello=25}", map.toString());
//		assertEquals(new Integer(25), map.get("hello"));

		StringReader reader = new StringReader("document");
		Iterable<Event> events = yaml.parse(reader);
		for (Event event : events) {
			System.out.println("event:" + event);
		}
	}
	
	@Test
	public void test2() {
		Yaml yaml = new Yaml();
		String document = "hello: 25\ngargamel: true";
		StringReader reader = new StringReader(document);
		Iterable<Node> nodes = yaml.composeAll(reader);
		for (Node node: nodes){
			System.out.println(node.getNodeId()+":"+node.getStartMark().getIndex());
			System.out.println(node);
		}
	}

	@Test
	public void testException() {
		Yaml yaml = new Yaml();
		try{
			String document = "hello:\t25";
			Map map = yaml.load(document);
		}catch(MarkedYAMLException e){
			Mark mark = e.getProblemMark();
			System.out.println("index:"+mark.getIndex());
			System.out.println("column:"+mark.getColumn());
			System.out.println("line:"+mark.getLine());
			
		}
	}
}
