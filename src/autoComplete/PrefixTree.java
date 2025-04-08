package autoComplete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * A prefix tree used for autocompletion. The root of the tree just stores links to child nodes (up
 * to 26, one per letter). Each child node represents a letter. A path from a root's child node down
 * to a node where isWord is true represents the sequence of characters in a word.
 */
public class PrefixTree {
    private TreeNode root;

    // Number of words contained in the tree
    private int size;

    public PrefixTree() {
        root = new TreeNode();
    }

    /**
     * Adds the word to the tree where each letter in sequence is added as a node
     * If the word, is already in the tree, then this has no effect.
     * @param word
     */
    public void add(String word){
        if (!contains(word)){
            size++;
            TreeNode tempRoot = root;
            for (int i = 0; i < word.length(); i++){
                
                Character curr = word.charAt(i);
                if (tempRoot.children.containsKey(curr)){
                    if (i==word.length()-1){
                        tempRoot.children.get(curr).isWord = true;
                        return;
                    }
                    else{
                        tempRoot = tempRoot.children.get(curr);
                    }
                }
                else{
                    tempRoot.children.put(curr, new TreeNode());
                    if (i==word.length()-1){
                        tempRoot.children.get(curr).isWord = true;
                        return;
                    }
                    else{
                        tempRoot = tempRoot.children.get(curr);
                    }

                }    
            }
        }
    }

    /**
     * Checks whether the word has been added to the tree
     * 
     * @param word
     * @return true if contained in the tree.
     */
    public boolean contains(String word) {
        TreeNode tempRoot = root;
        for (int i = 0; i < word.length(); i++) {
            Character curr = word.charAt(i);
            if (tempRoot.children.containsKey(curr)) {
                if (i == word.length() - 1 && tempRoot.children.get(curr).isWord) {
                    return true;
                } else if (i == word.length() - 1) {
                    return false;
                }
                tempRoot = tempRoot.children.get(curr);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds the words in the tree that start with prefix (including prefix if it is a word itself). The
     * order of the list can be arbitrary.
     * 
     * @param prefix
     * @return list of words with prefix
     */
    public ArrayList<String> getWordsForPrefix(String prefix) {
        // TODO: complete me
        return null;
    }

    /**
     * @return the number of words in the tree
     */
    public int size() {
        return size;
    }

}
