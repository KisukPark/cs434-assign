package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  ignore("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  ignore("adding ints") {
    assert(1 + 3 === 3)
  }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect between not intersected sets.") {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersect not contains 1")
      assert(!contains(s, 2), "Intersect not contains 2")
    }
  }

  test("intersect only contains elements which are both is `s` and `t`.") {
    new TestSets {
      val s = intersect(s1, s1)
      assert(contains(s, 1), "Intersect not contains 1")
      assert(!contains(s, 2), "Intersect not contains 2")
    }
  }

  test("diff only contains elements which are in `s` but not in `t`.") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "Diff contains 1")
      assert(!contains(s, 2), "Diff not contains 2")
    }
  }

  test("diff creates empty set with two same sets.") {
    new TestSets {
      val s = diff(s1, s1)
      assert(!contains(s, 1), "Diff not contains 1")
    }
  }

  test("filter only contains elements of `s` which satisfy predicate `p`.") {
    new TestSets {
      val s = filter(s1, (i: Int) => i == 1)
      assert(contains(s, 1), "Filter contains 1")
      assert(!contains(s, 2), "Filter not contains 2")
      assert(!contains(s, 3), "Filter not contains 3")
    }
  }

  test("filter only contains elements of `s` which `p` holds.") {
    new TestSets {
      val s = filter(s1, s1)
      assert(contains(s, 1), "Filter contains 1")
      assert(!contains(s, 2), "Filter not contains 2")
      assert(!contains(s, 3), "Filter not contains 3")
    }
  }

  test("forall tests.") {
    new TestSets {
      assert(forall(s1, (i: Int) => i == 1), "Forall returns true 1")
      assert(forall(s1, (i: Int) => i >= 0), "Forall returns true 2")
      assert(forall(s2, (i: Int) => contains(s2, i)), "Forall returns true 3")
      assert(!forall(s1, (i: Int) => i == 2), "Forall returns false")
    }
  }

  test("exists tests") {
    new TestSets {
      assert(exists(s1, (i: Int) => i >= 0), "Exists returns true 1")
      assert(!exists(s1, (i: Int) => i <= 0), "Exists returns false 1")
      assert(exists(union(s1, s2), (i: Int) => i == 2), "Exists returns true 2")
      assert(exists(diff(s1, s2), (i: Int) => i == 1), "Exists returns true 3r")
    }
  }

  test("map tests") {
    new TestSets {
      val s = map(s1, (i: Int) => -i)
      assert(contains(s, -1), "Map contains -1")
      assert(!contains(s, 1), "Map not contains 1")
    }
  }

  test("map tests 2") {
    new TestSets {
      val s = map(s2, (i: Int) => i * 2)
      assert(contains(s, 4), "Map contains 4")
      assert(!contains(s, 2), "Map not contains 2")
    }
  }
}
