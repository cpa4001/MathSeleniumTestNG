import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchList {
    public int LinearSearchIterative(List<Integer> list, int target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == target) {
                return i;
            }
        }
        return -1;
    }

    public int LinearSearchRecursive(List<Integer> list, int target, int index) {
        if (index == list.size()) {
            return -1;
        } if (list.get(index) == target) {
            return index;
        } else {
            return LinearSearchRecursive(list, target, index + 1);
        }
    }

    public int BinarySearchIterative(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2 );
            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int BinarySearchRecursive(List<Integer> list, int target, int left, int right) {
        int mid = left + ((right - left) / 2 );
        if (left > right) {
            return -1;
        } else if (list.get(mid) > target) {
            return BinarySearchRecursive(list, target, left, mid - 1);
        } else if (list.get(mid) < target) {
            return BinarySearchRecursive(list, target, mid + 1, right);
        } else {
            return mid;
        }
    }

    @Test
    public void LinearIterTest() {
        List<Integer> list = Arrays.asList(10, 8, 9, 2, 3, 4, 63, 23, 34, 345);
        Assert.assertEquals(LinearSearchIterative(list, 2),3 );
        Assert.assertEquals(LinearSearchIterative(list, 6000),-1);
        Assert.assertEquals(LinearSearchIterative(list, 9), 2);

    }

    @Test
    public void LinerRecurTest() {
        List<Integer> list = Arrays.asList(10, 8, 9, 2, 3, 4, 63, 23, 34, 345);
        Assert.assertEquals(LinearSearchRecursive(list, 9, 0), 2);
        Assert.assertEquals(LinearSearchRecursive(list, 2, 0),3);
        Assert.assertEquals(LinearSearchRecursive(list, 6000, 0), -1);
    }

    // have to sort for binary
    @Test
    public void BinIterTest() {
        List<Integer> list = Arrays.asList(1, 2, 4, 6, 23, 56, 78, 79, 87, 90, 101);
        Assert.assertEquals(BinarySearchIterative(list, 2),1 );
        Assert.assertEquals(BinarySearchIterative(list, 6000),-1);
        Assert.assertEquals(BinarySearchIterative(list, 101), list.size() - 1);

    }

    @Test
    public void BinRecurTest() {
        List<Integer> list = Arrays.asList(1, 2, 4, 6, 23, 56, 78, 79, 87, 90, 101);
        Assert.assertEquals(BinarySearchRecursive(list, 2, 0, list.size() - 1), 1);
        Assert.assertEquals(BinarySearchRecursive(list, 101, 0, list.size() - 1),list.size() - 1);
        Assert.assertEquals(BinarySearchRecursive(list, 6000, 0, list.size() - 1), -1);
    }
}
