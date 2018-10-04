package com.xyonix.mayetrix.mayu.wordnet;

import java.util.Arrays;

import com.xyonix.mayetrix.mayu.wordnet.BasicWordNet;

import junit.framework.TestCase;
import rita.wordnet.RiWordnet;

public class TestBasicWordNet extends TestCase {

	public TestBasicWordNet(String name) {
		super(name);
	}

	public void testContainsVerb() {
		assertTrue(BasicWordNet.hasVerb("love dogs"));
		assertTrue(BasicWordNet.hasVerb("water lawn"));
		assertFalse(BasicWordNet.hasVerb("supercalifragilicuousexpealidocious"));
	}

	public void testWordExpansion() throws Exception {
		RiWordnet wordnet = new RiWordnet();

		String word = "worked";
		String[] synonyms = wordnet.getStems(word, RiWordnet.VERB);

		System.out.println("syns for: " + word);
		if (synonyms != null) {
			Arrays.sort(synonyms);
			for (int i = 0; i < synonyms.length; i++) {
				System.out.println("syn " + i + ": " + synonyms[i]);
			}
		} else {
			System.out.println("No syns");
		} 
	}
}
