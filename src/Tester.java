import java.util.LinkedList;

public class Tester {
    public static void main(String args[])
    {
        Trie2 myTrie = new Trie2();
        myTrie.insertString("apple");
        myTrie.insertString("bike");
        myTrie.insertString("bake");
        myTrie.insertString("pen");
        myTrie.insertString("did");
        myTrie.insertString("ape");
        myTrie.insertString("child");
        myTrie.insertString("cat");
        myTrie.insertString("file");
        myTrie.insertString("hello");
        myTrie.insertString("he");
        myTrie.insertString("help");

        System.out.println("The sorted Trie: ");
        myTrie.printSorted();

        System.out.println("\nAll words prefixed by \"he\":");
        LinkedList myList = myTrie.wordsPrefixedBy("he");
        for(int x = 0; x < myList.size(); x++)
            System.out.println(myList.get(x));
    }
}
