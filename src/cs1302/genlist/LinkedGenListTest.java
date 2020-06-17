package cs1302.genlist;

import cs1302.genlistadt.GenList;
import cs1302.genlist.LinkedGenList;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.IntFunction;
import java.util.Comparator;

/**
 * This class demonstrates the proper use of methods
 * contained in the {@link LinkedGenList} class.
 *
 * Specifically, the {@code map}, {@code reduce}, {@code filter},
 * {@code min}, and {@code allMatch} methods are demonstrated.
 *
 */
public class LinkedGenListTest {

    /**
     * Main entry point.
     *
     * @param args  command line arguments (not functioning).
     */
    public static void main(String[] args) {

        demoMap();
        demoFilter();
        demoReduce();
        demoMin();
        demoAllMatch();

    }

    /**
     * Creates a string list with the numbers 0 through 5, inclusive.
     *
     * @return s {@code LinkedGenList} with strings 0 through 5.
     */
    private static GenList<String> getStrList() {
        GenList<String> s = new LinkedGenList<String>();
        
        s.add("0");
        s.add("1");
        s.add("2");
        s.add("3");
        s.add("4");
        s.add("5");

        return s;
    }

    /**
     * Creates a integer list with the numbers 0 through 5, inclusive.
     *
     * @return i {@code LinkedGenList} with integers 0 through 5.
     */
    private static GenList<Integer> getIntList() {
        GenList<Integer> i = new LinkedGenList<Integer>();

        for (int k = 0; k < 6; k++) {
            i.add(k);
        }

        return i;
    }

    /**
     * Transforms an element's value and changes its type.
     */
    private static void demoMap() {
        GenList<String> s = getStrList(); 
        GenList<Integer> i = getIntList(); 

        System.out.println("**Map Demonstration:");
        System.out.println("This list of Strings contains the integers 0 through 5.");
        System.out.println("Map will change each to an integer type and multiply it by 10.");

        Function<String, Integer> stringToInt = n -> {
            return Integer.parseInt(n) * 10;
        };

        GenList<Integer> intList = s.map(stringToInt);
        System.out.println("Old List: " + s.makeString(" "));
        System.out.println("Mapped List " + intList.makeString(" "));

        System.out.println("\nThe next demonstrate will take a list of integers, 0 through 5, and");
        System.out.print(" turn it into a String, and concatenate itself onto the end.\n");
        

        Function<Integer, String> intToString = n -> {
            return Integer.toString(n) + Integer.toString(n);
        };
        GenList<String> strList = i.map(intToString);
        System.out.println("Old List: " + i.makeString(" "));
        System.out.println("Mapped List: " + strList.makeString(" " ));
        System.out.println();
    }
    

    /**
     * Concatenates, adds, squares, multiplies, or otherwise
     * performs an operation on each member of the list.
     */
    private static void demoReduce() {
        GenList<String> s = getStrList(); 
        GenList<Integer> i = getIntList(); 
        
        System.out.println("*Reduce Demonstration:");
        
        BinaryOperator<String> quadCat = (x, y) -> {
            return x + y;
        };

        System.out.println("This will concatenate the contents of the list onto 'banana'");
        String st = s.reduce("banana", quadCat);
        System.out.println("Old List: " + s.makeString(" "));
        System.out.println("Reduced List: " + st); 

        System.out.println("\nThis will square the numbers from 0->5 and add the result to 100.");
        
        BinaryOperator<Integer> squared = (x, y) -> {
            return x + y * y;
        };
        Integer it = i.reduce(100, squared);
        System.out.println("Old List: " + i.makeString(" "));
        System.out.println("Reduced List: " + it);
    }


    /**
     * Filters based on boolean or multiple boolean expressions.
     */
    private static void demoFilter() {
        System.out.println("\nThis demo will print words that contain a but not e.");
        GenList<String> d = getStrList();
        d.add("apple");
        d.add("appl");
        d.add("banana");
        d.add("bananae");

        Predicate<String> p1 = n -> {
            return n.contains("a") && !n.contains("e");
        };

        GenList<String> dn = d.filter(p1);
        System.out.println("Old List: " + d.makeString(" "));
        System.out.println("Filtered List: " + dn.makeString(" "));

        System.out.println("\nThis demo will print numbers divisible by 2 and 5");
        GenList<Integer> i = getIntList();
        i.add(50);
        i.add(55);
        i.add(60);
        i.add(100);
        i.add(102);
        i.add(2);

        Predicate<Integer> p2 = n -> {
            return n % 2 == 0 && n % 5 == 0;
        };

        GenList<Integer> in = i.filter(p2);
        System.out.println("Old List: " + i.makeString(" " ));
        System.out.println("Filtered List: " + in.makeString(" " ));

    }


    /**
     * Finds minimum based on parts/digits of the
     * elements.
     */
    private static void demoMin() {
        GenList<Integer> i = new LinkedGenList<Integer>();
        i.add(25);
        i.add(5009);
        i.add(9);
        i.add(101);

        GenList<Double> d = new LinkedGenList<Double>();
        d.add(-100.405);
        d.add(5000.33);
        d.add(5001.012);
        d.add(-11550.123);
        d.add(-11512.1512);

        System.out.println("\nThe first demo will take a ");
        System.out.println("list of integers and sort it based on their ones digit.\n");
        Comparator<Integer> comp = (x, y) -> {
            x = x % 10;
            y = y % 10;
            return x.compareTo(y);
        };

        Integer i2 = i.min(comp);
        System.out.println("Old List: " + i.makeString(" "));
        System.out.println("Smallest 1s Digit: " + i2);

        System.out.println("\nThis will take a double and sort it based on ");
        System.out.print("their absolute value.\n");
        System.out.println("Note that the Math.abs() function is not used.");
        Comparator<Double> dubs = (x, y) -> {
            if (x < 0) {
                x = x * -1;
            }
            if (y < 0) {
                y = y * -1;
            }

            return x.compareTo(y);
        };

        Double d2 = d.min(dubs);

        System.out.println("Old List" + d.makeString(" "));
        System.out.println("Min: " + d2);
    }


    /**
     * Determines if all elements match.
     */
    private static void demoAllMatch() {
        GenList<Integer> i = new LinkedGenList<Integer>();
        GenList<String> s = new LinkedGenList<String>();
        GenList<String> s2 = new LinkedGenList<String>();

        System.out.println("\nThis will match a list of strings if they contain 'o' ");
        System.out.print("or are 5 characters or less."); 
        s.add("banana");
        s.add("boat");
        s.add("oats");
        s.add("nob");
        s.add("knobb");
        s.add("kknob");
        
        Predicate<String> p1 = n -> {
            return n.contains("o") || n.length() <= 5;
        };
        System.out.println("Old List: " + s.makeString(" "));
        if (s.allMatch(p1)) {
            System.out.println("All match!");
        } else {
            System.out.println("Oops!  Removing the bad word!");
        }
        s.remove(0);
        if (s.allMatch(p1)) {
            System.out.println("All Match Passed! " + s.makeString(" "));
        }

        System.out.println("This test will simply match if all integers are between 1 and 10.");
        i.add(2);
        i.add(4);
        i.add(6);
        i.add(10);

        Predicate<Integer> p2 = n -> {
            return n >= 1 && n <= 10 && n % 2 == 0;
        };

        if (i.allMatch(p2)) {
            System.out.println("All match!: " + i.makeString(" "));
        }

        System.out.println("But lets break this and see if it works by adding 3.");
        i.add(3);

        if (i.allMatch(p2)) {
            System.out.println("Still matches.");
        } else {
            System.out.println("Oops. Broken\n" + i.makeString(" "));
        }

    }
}
