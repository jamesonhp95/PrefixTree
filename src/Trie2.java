/*
Author: Jameson Price
This is a java implementation of a Trie used to store words.
Each TrieNode has a TreeMap of children associated with it and a boolean
variable dictating that the path to that child is a word.
 */

import java.util.*;

public class Trie2 {

    private class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();
        boolean isWord = false;
    }

    private TrieNode root;
    public Trie2() {
        this.root = new TrieNode();
    }

    /**
     * Public method for users to insert a string into the Trie2 class.
     * @param s the word to insert.
     */
    public void insertString(String s) {
        insertString(root, s);
    }

    /**
     * This method first traverses the Trie until a child might be added that isn't already
     * contained in the tree. At this point in time, we begin making new TrieNodes into the Trie
     * associated with the provided word s.
     * When the string has been completed inserted into the Trie, we set the boolean variable of isWord
     * to true to signify the path above that node is a word.
     * @param root The root of the passed in Trie branch, initially the root of the entire Trie.
     * @param s The word to be inserted
     */
    private void insertString(TrieNode root, String s) {
        TrieNode cur = root;
        for (char ch : s.toCharArray()) {
            TrieNode next = cur.children.get(ch);
            if (next == null)
                cur.children.put(ch, next = new TrieNode());
            cur = next;
        }
        cur.isWord = true;
    }

    public LinkedList wordsPrefixedBy(String p)
    {
        return wordsPrefixedBy(this.root, p);
    }

    /**
     * This method searches the Trie for all words that are prefixed by the provided string.
     * First it gets to the node in the Trie that is the last character in the prefix string,
     * then creates a LinkedList to add words all unique words below that point to by checking
     * if the marker isWord is set to true on deeper TrieNodes, then utilizes the helper method
     * addWordsFromPrefix to recursively traverse all paths down the Trie.
     * @param root The root of the passed in Trie branch, initially the root of the entire Trie.
     * @param p The prefix string provided by the user.
     * @return A list of all words contained in the Trie prefixed by p
     */
    private LinkedList wordsPrefixedBy(TrieNode root, String p)
    {
        TrieNode prefixNode = getNode(root, p);
        LinkedList returnList = new LinkedList();
        if(prefixNode.isWord)
            returnList.add(p);
        addWordsFromPrefix(prefixNode, returnList, p);
        return returnList;
    }

    /**
     * Traverses down the Trie, checking every child and their paths to add words to the passed in
     * list, thus creating a complete list of all words prefixed by pref.
     * @param prefixNode The root of the passed in Trie branch, initially the branch where the ending character of the prefix was found.
     * @param aList The list to add words to.
     * @param pref The prefix given by the user, which will be adjusted to concatenate characters as we traverse to new TrieNodes
     */
    private void addWordsFromPrefix(TrieNode prefixNode, LinkedList aList, String pref)
    {
        for(Character ch : prefixNode.children.keySet())
        {
            TrieNode child = prefixNode.children.get(ch);
            String newPref = pref + ch;
            if(child.isWord)
            {
                aList.add(newPref);
            }
            if(child != null)
            {
                addWordsFromPrefix(child, aList, newPref);
            }
        }
    }

    /**
     * This method returns the TrieNode where the last character in the provided prefix p resides.
     * @param root The root of the passed in Trie branch, initially the root of the entire Trie.
     * @param p The prefix to search for, which will be stripped of the first character until p.length == 0
     * @return The TrieNode where the last character in the provided prefix p resides.
     */
    private TrieNode getNode(TrieNode root, String p)
    {
        if(p != null && p.length() > 0)
        {
            String rest = p.substring(1);
            char ch = p.charAt(0);
            TrieNode child = root.children.get(ch);
            if(p.length() == 1 && child != null)
                return child;
            else
                return getNode(child, rest);
        }
        else
            return root;
    }

    public void printSorted() {
        printSorted(root, "");
    }

    /**
     * Prints the Trie sorted.
     * @param node The root of the passed in Trie branch, initially the root of the entire Trie.
     * @param s The current word that is constructed as we traverse the Trie recursively, initially empty.
     */
    private void printSorted(TrieNode node, String s) {
        if (node.isWord) {
            System.out.println(s);
        }
        for (Character ch : node.children.keySet()) {
            printSorted(node.children.get(ch), s + ch);
        }
    }

    /**
     * This is the public method for users to find out if words exist in a given Trie
     * That have the given prefix. This method merely takes the string provided by the
     * user and passes it to the private method within the class.
     * @param s prefix defined by the user.
     * @return true if the tree contains a word with prefix s.
     */
    public boolean findPrefix(String s) {
        return findPrefix(root, s);
    }

    /**
     * This is the private method that recursively searches through the Trie
     * to determine if any words exist with prefix s. Then the string is split into a char array
     * so that we can determine if the first character in the rest of the string is a child. If it is and
     * the length of the string is 1, we return true since there is a word within the Trie associated with the given prefix.
     * Otherwise, if the child is null we return false, since the character doesn't exist in the current set of children for
     * the current node. Finally, we recursively call findPrefix passing the child and the rest of the current string prefix.
     * @param node initially the root, but passes the child node that has character ch, a part of the provided string by the user.
     * @param s prefix defined by the user.
     * @return true if the tree contains a word with prefix s. False otherwise.
     */
    private boolean findPrefix(TrieNode node, String s) {
        if(s != null) {
            String rest = s.substring(1);
            char ch = s.charAt(0);
            TrieNode child = node.children.get(ch);
            if(s.length() == 1 && child != null)
                return true;
            if(child == null)
                return false;
            else
                return findPrefix(child, rest);
        }
        return false;
    }
}
