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

}
