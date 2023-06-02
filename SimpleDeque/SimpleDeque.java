// T为泛型
public class SimpleDeque <T> implements DequeInterface<T> {
	private static final String UNCHECKED = "unchecked";  // 告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息
	private T[] deque;
	private int frontIndex;
	private int backIndex;
	private static final int DEFAULT_INITIAL_CAPACITY = 30;
	
	public SimpleDeque() {
		this(DEFAULT_INITIAL_CAPACITY);  // this()在同一个类中调用另一个构造函数
	}
	
	public SimpleDeque(int initialCapacity) {
		@SuppressWarnings(UNCHECKED)  // 告诉编译器忽略指定的警告，不用在编译完成后出现警告信息
		T[] deque1 = (T[]) new Object[initialCapacity];  // 创建一个初始容量为initialCapacity的Object数组，然后通过强制类型转换(T[])将其转换为指定类型的T[]数组，从而创建一个双端队列deque1
		deque = deque1;
		frontIndex = 0;
		backIndex = initialCapacity - 1;
	}
	
	//扩容
	@SuppressWarnings("unused")
	private void enlargeCapacity() {
		// frontIndex和backIndex之间的元素数量为(backIndex - frontIndex + deque.length) % deque.length + 1，其中%为取模运算，可以避免负数的出现。当队列已满时，上述元素数量等于deque.length，即队列元素数量已经等于数组的容量。
		// 为了避免数组溢出，需要在数组末尾预留一个空位。因此，当队列已满时，数组中的下一个可用位置应该为(backIndex + 2) % deque.length
		if(frontIndex == (backIndex + 2) % deque.length){
			T[] initialDeque = deque;
			int initialSize = initialDeque.length;
			
			@SuppressWarnings(UNCHECKED)
			T[] Deque1;  // 创建一个新的泛型数组Deque1来存储扩容后的双端队列，长度为原来的两倍
			
			for(int i = 0; i < initialSize - 1; i++) {
				deque[i] = initialDeque[frontIndex];
				frontIndex = (frontIndex + 1) % initialSize;  // 如果frontIndex为initialSize-1（即指向数组的最后一个元素），则计算后的新frontIndex为0（即指向数组的第一个元素），否则frontIndex增加1，指向下一个元素
			}
			
			frontIndex = 0;
			backIndex = initialSize - 2;  // 因为Deque1数组的长度为原来的两倍，所以需要减去2，以保留一个空闲位置
		}
	}
	
	// add at front
	public void addToFront(T newEntry) {
		enlargeCapacity();
		if(deque[frontIndex] != null && frontIndex == ((backIndex + 1) % deque.length)) {
			System.out.println("No space, " + newEntry + "can't be added");
		}else{
			if(frontIndex == 0)  // 队列的逻辑前端
				frontIndex = deque.length - 1;  // 需要添加的索引位置
			else frontIndex = frontIndex - 1;  // 前面的frontIndex是需要添加的位置，后面的frontIndex是队列的逻辑前端
			deque[frontIndex] = newEntry;
		}
	}
	
	// add at back
	public void addToBack(T newEntry) {
		enlargeCapacity();
		if(deque[backIndex] != null && frontIndex == ((backIndex + 1) % deque.length)) {
			System.out.println("No space, " + newEntry + "can't be added");
		}else {
			backIndex = (backIndex + 1) % deque.length;
			deque[backIndex] = newEntry;
		}
	}
	
	// remove from front
	public T removeFront() {
		T front = null;
		
		if(!isEmpty()) {
			front = deque[frontIndex];
			deque[frontIndex] = null;
			frontIndex = (frontIndex + 1) % deque.length;
		}
		
		return front;
	}
	
	// remove from back
	public T removeBack() {
		T back = null;
		
		if(!isEmpty()) {
			back = deque[backIndex];
			deque[backIndex] = null;
			if(backIndex == 0) backIndex = deque.length - 1;
			else backIndex = (backIndex - 1);
		}
		
		return back;
	}
	
	// get front item
	public T getFront() {
		if(!isEmpty()) return deque[frontIndex];
		
		return null;
	}
	
	// get back item
	public T gerback() {
		if(!isEmpty()) return deque[backIndex];
		
		return null;
	}
	
	// is deque empty?
	public boolean isEmpty() {
		// 判断队列前端索引frontIndex是否与后端索引backIndex相邻。如果相邻，说明队列中只有一个元素或者队列为空。队列中至少需要两个索引才能存储一个元素。
		return (frontIndex == ((backIndex + 1) % deque.length)) && deque[frontIndex] == null && deque[backIndex] == null;
	}
	
	// reset dequee
	public void clear() {
		while(!isEmpty()) removeBack();  // 循环移除后端元素
	}
	
	// 转化成字符串形式输出
	public String toString() {
		String ziFuChuan = "";
		if(isEmpty()) return "The deque is empty.";
		int i = frontIndex;  // 当前位置
		int flag = 0;  // 标记位
		while(i != frontIndex || (flag == 0)) {
			flag = 1;
			if(deque[i] == null) {  // 判断当前位置是否为空，若为空，则将i加1继续循环;若不为空，执行String.valueOf
				i = (i + 1) % deque.length;
				continue;
			}
			ziFuChuan += String.valueOf(deque[i]) + ", ";
			i = (i + 1) % deque.length;
		}
		return ziFuChuan;
	}

	@Override
	public T getBack() {
		return null;
	}
}