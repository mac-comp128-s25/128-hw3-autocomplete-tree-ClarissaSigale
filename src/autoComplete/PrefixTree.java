package autoComplete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import apple.laf.JRSUIUtils.Tree;

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
        TreeNode tempRoot = root;
        if (!contains(word)){
            for (int i = 0; i < word.length(); i++){
                Character curr = word.charAt(i);
                if (!tempRoot.children.containsKey(curr)){
                    TreeNode newNode = new TreeNode();
                    newNode.letter = curr;
                    tempRoot.children.put(curr, newNode);
                }
                tempRoot = tempRoot.children.get(curr);
            }
            if (!tempRoot.isWord){
                size++;
                tempRoot.isWord = true;
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
            if (!tempRoot.children.containsKey(curr)) {
                return false;
            }
            tempRoot = tempRoot.children.get(curr);
        }
        return tempRoot.isWord;
    }

    /**
     * Finds the words in the tree that start with prefix (including prefix if it is a word itself). The
     * order of the list can be arbitrary.
     * 
     * @param prefix
     * @return list of words with prefix
     */
    public ArrayList<String> getWordsForPrefix(String prefix) {
        ArrayList<String> results = new ArrayList<>();
        TreeNode tempRoot = root;

        if (!contains(prefix)){
            return results;
        }
        for (int i = 0; i<prefix.length(); i++){
            Character curr = prefix.charAt(i);
            tempRoot = tempRoot.children.get(curr);
        }

        getWordsHelper(tempRoot, prefix, results);
        return results;
    }

    private void getWordsHelper(TreeNode tempRoot, String word, ArrayList<String> results){
        if (tempRoot.isWord){
            results.add(word);
        }

        for (Map.Entry<Character, TreeNode> entry : tempRoot.children.entrySet()){
            getWordsHelper(entry.getValue(), word+entry.getKey(), results);
        }
    }





    /**
     * @return the number of words in the tree
     */
    public int size() {
        return size;
    }

}
