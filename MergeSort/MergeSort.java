import java.util.Stack;

public class MergeSort<T extends Comparable<? super T>> {
    Stack<Frame<T>> s;
    MergeSort() {
	s = new Stack<>();
    }

    // Stack frame has all the information needed for 1 instance of Merge sort
    class Frame<E extends Comparable<? super E>> {
	E[] arr, tmp;
	int p, q, r, state;
	Frame(E[] arr, E[] tmp, int p, int q, int r, int state) {
	    this.arr = arr;
	    this.tmp = tmp;
	    this.p = p;
	    this.q = q;
	    this.r = r;
	    this.state = state;
	}
    }

    void nonRecursiveMergeSort(T[] arr, T[] tmp, int left, int right) {
	s.push(new Frame<T>(arr, tmp, left, 0, right, 0));
	while(!s.isEmpty()) {
	    Frame<T> current = s.peek();
	    switch(current.state) {
	    case 0:								/* state 0 */
		if(current.p < current.r) {					// if (p < r) {
		    current.q = (current.p + current.r)/2;			//     int q = (p+r)/2;
		    s.push(new Frame<T>(arr, tmp, current.p, 0, current.q, 0));	//     mergeSort(arr, tmp, p, q);
		    current.state = 1;
		} else {
		    s.pop();
		}
		break;
	    case 1:						     	        /* state 1 */
		s.push(new Frame<T>(arr, tmp, current.q+1, 0, current.r, 0));	//     mergeSort(arr, tmp, q+1, r);
		current.state = 2;
		break;
	    case 2:								/* state 2 */
		merge(arr, tmp, current.p, current.q, current.r);		//     merge(arr, tmp, p, q, r);
		s.pop();
		break;
	    }
	}
    }

    static<T extends Comparable<? super T>> void nonRecursiveMergeSort(T[] arr, T[] tmp) {
	MergeSort<T> sort = new MergeSort<>();
	sort.nonRecursiveMergeSort(arr, tmp, 0, arr.length - 1);
    }

    static<T extends Comparable<? super T>> void merge(T[] arr, T[] tmp, int p, int q, int r) {
        int i = p; int j = q+1;
        for(int k=p; k<=r; k++) {
	    if (j > r || (i <= q && arr[i].compareTo(arr[j]) <= 0)) {
		tmp[k] = arr[i++];
	    } else {
		tmp[k] = arr[j++];
	    }
	}
        for(int k=p; k<=r; k++) arr[k] = tmp[k];
        return;
    }

    public static void main(String[] args) {
        int n = 10;
	if(args.length > 0) { n = Integer.parseInt(args[0]); }
        Integer[] arr = new Integer[n];  Integer[] tmp = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer(i);
        }

	Shuffle.shuffle(arr, 0, n-1);
	Shuffle.printArray(arr, 0, n-1, "Before: ");

	Timer timer = new Timer();
	timer.start();

	//mergeSort(arr, tmp, 0, n-1);
        nonRecursiveMergeSort(arr, tmp);
	Shuffle.printArray(arr, 0, n-1, "After: ");
	System.out.println(timer.end());
    }
}

/*
Sample output:
Before:  4 5 0 7 8 6 3 9 1 2
After:  0 1 2 3 4 5 6 7 8 9
Time: 1 msec.
Memory: 2 MB / 250 MB.
*/