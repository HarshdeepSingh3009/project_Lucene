/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lucene_1;

import com.oracle.nio.BufferSecrets;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import jdk.nashorn.internal.objects.Global;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

/**
 *
 * @author Harsh
 */
public class Lucene_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String dataDirPath = "C:\\Users\\Harsh\\Desktop\\Corpus"; // the collection of files from which to make index
        File[] files = new File(dataDirPath).listFiles();
        Indexer index = new Indexer();
        for (int i = 0; i < files.length; i++) {
            index.indexDoc(files[i]);
        }
        index.closeIndexWriter();
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a query: ");
        String query = reader.next();
        Search(query);
    }
            
    // performs search for the given query
    public static void Search(String query) {
        try {
            SearchEngine se = new SearchEngine();
            TopDocs topDocs = se.performSearch(query, 20);
            // obtain the ScoreDoc (= documentID, relevanceScore) array from topDocs
            ScoreDoc[] hits = topDocs.scoreDocs;

            // retrieve each matching document from the ScoreDoc arry
            for (int i = 0; i < hits.length; i++) {
                Document doc = se.getDocument(hits[i].doc);
                String docName = doc.get("name");
                System.out.println(docName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
    }

}
