/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lucene_1;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author Harsh
 */

// filters all the .txt files in the folder
  public class TextFileFilter implements FileFilter {

   @Override
   public boolean accept(File pathname) {
      return pathname.getName().toLowerCase().endsWith(".txt");
   }
}

