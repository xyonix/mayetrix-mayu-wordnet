package com.xyonix.mayetrix.mayu.wordnet;

import java.util.List;

import rita.wordnet.RiWordnet;

import com.xyonix.mayetrix.mayu.core.FrequencyCounter;
import com.xyonix.mayetrix.mayu.core.Term;

public class BasicWordNet {

	private static RiWordnet riWordnet = new RiWordnet();

	/**
	 * Returns synonyms for a specified verb.
	 */
	public static String[] getSynonymsForVerb(String verb) {
		String[] syns = riWordnet.getAllVerbGroups(verb, RiWordnet.VERB);
		if(syns==null)
			return new String[0];
		return syns;
	}

	/**
	 * Returns stem for a verb.
	 */
	public static String getWordStemForVerb(String verb) {
		String[] stems = riWordnet.getStems(verb, RiWordnet.VERB);
		if(stems==null) {
			stems = new String[1];
			stems[0]=verb;
		}
		return stems[0];
	}

	/**
	 * Returns true if a given noun phrase contains a verb.
	 */
	public static boolean hasVerb(String phrase) {
		if(phrase==null || phrase.length()<2)
			return false;

		String[] parts = phrase.split("\\s+");
		for(String p:parts) {
			if(riWordnet.isVerb(p))
				return true;
		}

		return false;
	}

	/**
	 * Returns antonyms for a word.
	 */
	public static String[] getAntonyms(String word) {
		if(riWordnet.isAdjective(word))
			return riWordnet.getAllAntonyms(word, RiWordnet.ADJ);
		if(riWordnet.isVerb(word))
			return riWordnet.getAllAntonyms(word, RiWordnet.VERB);
		if(riWordnet.isAdverb(word))
			return riWordnet.getAllAntonyms(word, RiWordnet.ADV);

		return riWordnet.getAllAntonyms(word, RiWordnet.NOUN);
	}

	/**
	 * REturns synonyms for a word.
	 */
	public static String[] getSynonyms(String word) {
		if(riWordnet.isVerb(word))
			return riWordnet.getAllSynonyms(word, RiWordnet.VERB);
		if(riWordnet.isAdverb(word))
			return riWordnet.getAllSynonyms(word, RiWordnet.ADV);
		if(riWordnet.isAdjective(word))
			return riWordnet.getAllSynonyms(word, RiWordnet.ADJ);

		return riWordnet.getAllSynonyms(word, RiWordnet.NOUN);
	}

	public static List<Term> generateSeedSynonyms(String[] seeds) {
		FrequencyCounter tc = new FrequencyCounter();
		int i=0;
		for(String s:seeds) {
			tc.update(s, 50000f-(5000*i++));

			String[] syns = getSynonyms(s);
			if(syns!=null) {
				for(String syn:syns) {
					tc.update(syn);
					String[] syn2ds = getSynonyms(syn);
					if(syn2ds!=null) {
						for(String syn2d:syn2ds) {
							tc.update(syn2d);
						}
					}
				}
			}
		}
		return tc.getAllRankedByFrequency();
	}
}
