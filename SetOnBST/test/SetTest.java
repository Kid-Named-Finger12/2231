import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Jonathan Strunck, Benjamin Huang
 *
 */
public abstract class SetTest {
    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    @Test
    public void constructorTestEmpty() {
        Set<String> set = this.constructorTest();
        Set<String> setExpected = this.constructorRef();
        assertEquals(set, setExpected);
    }

    @Test
    public void constructorTestString() {
        Set<String> set = this.createFromArgsTest("abcde");
        Set<String> setExpected = this.createFromArgsRef("abcde");
        assertEquals(set, setExpected);
    }

    @Test
    public void addTestEmpty() {
        Set<String> set = this.constructorTest();
        set.add("abcde");
        Set<String> setExpected = this.constructorRef();
        setExpected.add("abcde");
        assertEquals(set, setExpected);
    }

    @Test
    public void addTestExistingString() {
        Set<String> set = this.createFromArgsTest("abcde");
        set.add("efg");
        Set<String> setExpected = this.createFromArgsRef("abcde");
        setExpected.add("efg");
        assertEquals(set, setExpected);
    }

    @Test
    public void removeTest() {
        Set<String> set = this.createFromArgsTest("abcde");
        set.remove("abcde");
        Set<String> setExpected = this.constructorRef();
        assertEquals(set, setExpected);
    }

    @Test
    public void removeAnyTest() {
        Set<String> set = this.createFromArgsTest("abcde");
        set.removeAny();
        Set<String> setExpected = this.createFromArgsRef("abcde");
        setExpected.removeAny();
        assertEquals(set, setExpected);
    }

    @Test
    public void containsTestFalse() {
        Set<String> set = this.createFromArgsTest("abcde");
        boolean containsOutput = set.contains("edcba");
        assertEquals(containsOutput, false);
    }

    @Test
    public void containsTestTrue() {
        Set<String> set = this.createFromArgsTest("abcde");
        boolean containsOutput = set.contains("abc");
        assertEquals(containsOutput, true);
    }

    @Test
    public void containsTestSameString() {
        Set<String> set = this.createFromArgsTest("abcde");
        boolean containsOutput = set.contains("abcde");
        assertEquals(containsOutput, true);
    }

    @Test
    public void containsTestCounterContain() {
        Set<String> set = this.createFromArgsTest("abcde");
        boolean containsOutput = set.contains("+abcde+");
        assertEquals(containsOutput, false);
    }

    @Test
    public void sizeTestEmpty() {
        Set<String> set = this.constructorTest();
        int sizeExpected = 0;
        assertEquals(set.size(), sizeExpected);
    }

    @Test
    public void sizeTestNotEmpty() {
        Set<String> set = this.createFromArgsTest("abcde");
        int sizeExpected = "abcde".length();
        assertEquals(set.size(), sizeExpected);
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

}
