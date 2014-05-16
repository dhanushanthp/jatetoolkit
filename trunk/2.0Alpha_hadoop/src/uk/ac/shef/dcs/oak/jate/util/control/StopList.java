package uk.ac.shef.dcs.oak.jate.util.control;

import uk.ac.shef.dcs.oak.jate.JATEProperties;

import java.util.HashSet;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Represents a stop word list. These are the words which usually should not occur in a valid term
 *
 * @author <a href="mailto:z.zhang@dcs.shef.ac.uk">Ziqi Zhang</a>
 */


public class StopList extends HashSet<String> {

	private boolean _caseSensitive;

	/**
	 * Creates an instance of stop word list
	 * @param caseSensitive whether the list should ignore cases
	 * @throws IOException
	 */
	public StopList (final boolean caseSensitive) throws IOException {
		super();
		_caseSensitive =caseSensitive;
		String nlpPath = JATEProperties.getInstance().getNLPPath();
		URL url = this.getClass().getResource(nlpPath + "/stoplist.txt");
		loadStopList(url, _caseSensitive);
	}

	/**
	 * @param word
	 * @return true if the word is a stop word, false if otherwise
	 */
	public boolean isStopWord(String word){
		if(_caseSensitive) return contains(word.toLowerCase());
		return contains(word);
	}

	private void loadStopList(final URL stopListFile, final boolean lowercase) throws IOException {
      final BufferedReader reader = new BufferedReader(new InputStreamReader(
				stopListFile.openStream()));
      String line;
      while ((line = reader.readLine()) != null) {
         line = line.trim();
         if (line.equals("") || line.startsWith("//")) continue;
         if(lowercase) this.add(line.toLowerCase());
	      else this.add(line);
      }
   }

}
