/*
1. setup
2. login
3. close
 */

import org.testng.Assert;
import org.testng.annotations.Test;
public class FirstTestCase {

    // anything with test decorator will be a unit test and use asssert
    // other methods will be ur methods to test

    public int add(int a, int b) {
        return a + b;
    }

    @Test
    void setup() {
        System.out.println("This is a test");
    }

    @Test
    void login() {
        System.out.println("This is a login test");
    }

    @Test
    void teardown() {
        System.out.println("closing.");
    }

    @Test
    void testAdd(){
        Assert.assertEquals(2, add(1,1));
    }

}