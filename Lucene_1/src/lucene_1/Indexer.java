/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lucene_1;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
/**
 *
 * @author Harsh
 */
public class Indexer {
    private IndexWriter indexWriter = null;     
    private String pth = "C:\\Users\\Harsh\\Desktop\\index";
            //create the indexer
          public Indexer() {
    }
     
    public IndexWriter getIndexWriter(boolean create) throws IOException {
       if(indexWriter == null){
             File f = new File(pth);
             Path p = Paths.get(f.getPath());
             //this directory will contain the indexes
             Directory indexDirectory = FSDirectory.open(p);
            IndexWriterConfig config = new IndexWriterConfig( new StandardAnalyzer());
            indexWriter = new IndexWriter(indexDirectory, config);
       }
        return indexWriter;
   }
    

    public void closeIndexWriter() throws IOException {
        if (indexWriter != null) {
            indexWriter.close();
        }
    }
      public void indexDoc(File file) throws IOException {

        System.out.println("Indexing file: " + file.getName());
        IndexWriter writer = getIndexWriter(true);
        Document doc = new Document();
        doc.add(new StringField("name", file.getName(), Field.Store.YES));
       //index file contents
        Field contentField = new Field("Content", new FileReader(file),TextField.TYPE_NOT_STORED);
        doc.add(contentField);
        writer.addDocument(doc);
    }
}
