// CS 0445 Spring 2023
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a circular, doubly linked list of nodes.  See more comments below on the
// specific requirements for the class.

// You should use this class as the starting point for your implementation. 
// Note that all of the methods are listed -- you need to fill in the method
// bodies.  Note that you may want to add one or more private methods to help
// with your implementation -- that is fine.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
	// These are the only two instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	// Note: This method is implemented for you.  See code below.  Also read
	// the comments.  The code here may be helpful for some of your other
	// methods.
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0)  // special case for empty String
		{
			firstC = null;
			length = 0;
		}
		else
		{
			firstC = new CNode(s.charAt(0));  // create first node
			length = 1;
			CNode currNode = firstC;
			// Iterate through remainder of the String, creating a new
			// node at the end of the list for each character.  Note
			// how the nodes are being linked and the current reference
			// being moved down the list.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));  // create Node
				currNode.next = newNode;  	// link new node after current
				newNode.prev = currNode;	// line current before new node
				currNode = newNode;			// move down the list
				length++;
			}
			// After all nodes are created, connect end back to front to make
			// list circular
			currNode.next = firstC;
			firstC.prev = currNode;
		}
	}

	// Return the entire contents of the current MyStringBuilder as a String
	// For this method you should do the following:
	// 1) Create a character array of the appropriate length
	// 2) Fill the array with the proper characters from your MyStringBuilder
	// 3) Return a new String using the array as an argument, or
	//    return new String(charArray);
	// Note: This method is implemented for you.  See code below.
	public String toString()
	{
		char [] c = new char[length];
		int i = 0;
		CNode currNode = firstC;
		
		// Since list is circular, we cannot look for null in our loop.
		// Instead we count within our while loop to access each node.
		// Note that in this code we don't even access the prev references
		// since we are simply moving from front to back in the list.
		while (i < length)
		{
			c[i] = currNode.data;
			i++;
			currNode = currNode.next;
		}
		return new String(c);
	}

	// Create a new MyStringBuilder initialized with the chars in array s. 
	// You may NOT create a String from the parameter and call the first
	// constructor above.  Rather, you must build your MyStringBuilder by
	// accessing the characters in the char array directly.  However, you
	// can approach this in a way similar to the other constructor.
	public MyStringBuilder(char [] s)
	{
		if (s == null || s.length == 0) {
	        firstC = null;
	        length = 0;
	    } else {
	        firstC = new CNode(s[0]);  // 创建第一个节点，并将其设为firstC
	        length = 1;
	        CNode currNode = firstC;
	        // 遍历字符数组，创建节点并建立链接
	        for (int i = 1; i < s.length; i++) {
	            CNode newNode = new CNode(s[i]);
	            currNode.next = newNode;  // 当前节点指向新节点
	            newNode.prev = currNode;  // 新节点指向当前节点
	            currNode = newNode;  // 当前节点移动到新节点
	            length++;
	        }
	        // 最后一个节点的next指向firstC，将firstC的prev指向最后一个节点，形成循环链表
	        currNode.next = firstC;
	        firstC.prev = currNode;
	    }
	}
	
	// Copy constructor -- make a new MyStringBuilder from an old one.  Be sure
	// that you make new nodes for the copy (traversing the nodes in the original
	// MyStringBuilder as you do)
	public MyStringBuilder(MyStringBuilder old)
	{
		if (old == null || old.length == 0) {
	        firstC = null;
	        length = 0;
	    } else {
	        firstC = new CNode(old.firstC.data);
	        length = 1;
	        CNode oldCurrNode = old.firstC.next;
	        CNode newCurrNode = firstC;
	        // 遍历旧对象的链表，创建节点并建立链接
	        while (oldCurrNode != old.firstC) {
	            CNode newNode = new CNode(oldCurrNode.data);
	            newCurrNode.next = newNode;  // 新对象当前节点的next指向新节点
	            newNode.prev = newCurrNode;  // 新节点的prev指向新对象当前节点
	            newCurrNode = newNode;  // 更新新对象当前节点为新节点
	            oldCurrNode = oldCurrNode.next;  // 更新旧对象当前节点为下一个节点
	            length++;
	        }
	        newCurrNode.next = firstC;
	        firstC.prev = newCurrNode;
	    }
	}
	
	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		firstC = null;
	    length = 0;
	}
	
	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!  Note
	// that you cannot simply link the two MyStringBuilders together -- that is
	// very simple but it will intermingle the two objects, which you do not want.
	// Thus, you should copy the data in argument b to the end of the current
	// MyStringBuilder.
	public MyStringBuilder append(MyStringBuilder b)
	{
		if (b != null && b.length > 0) {
	        if (firstC == null) {  // 若当前对象为空
	            firstC = new CNode(b.firstC.data);  // b对象的第一个节点的数据
	            length = 1;
	            CNode bCurrNode = b.firstC.next;
	            CNode currNode = firstC;
	            while (bCurrNode != b.firstC) {
	                CNode newNode = new CNode(bCurrNode.data);  // 创建一个新的节点 newNode，并将 bCurrNode 的数据赋值给 newNode
	                currNode.next = newNode;  // 将 currNode 的 next 指针指向 newNode，建立链接
	                newNode.prev = currNode;  // 将 newNode 的 prev 指针指向 currNode，建立双向链接
	                currNode = newNode;  // 更新 currNode 为 newNode，以便在下一次循环中连接下一个节点
	                bCurrNode = bCurrNode.next;  
	                length++;
	            }
	            currNode.next = firstC;
	            firstC.prev = currNode;
	        } else {
	            CNode bCurrNode = b.firstC;
	            for (int i = 0; i < b.length; i++) {
	                CNode newNode = new CNode(bCurrNode.data);
	                CNode lastNode = firstC.prev;  // 获取当前链表的最后一个节点 lastNode，即 firstC 的前一个节点
	                lastNode.next = newNode;
	                newNode.prev = lastNode;
	                newNode.next = firstC;
	                firstC.prev = newNode;
	                lastNode = newNode;
	                bCurrNode = bCurrNode.next;
	                length++;
	            }
	        }
	    }
	    return this;
	}

	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)
	{
		if (s != null && s.length() > 0) {
	        if (firstC == null) {
	            firstC = new CNode(s.charAt(0));
	            length = 1;
	            CNode currNode = firstC;
	            for (int i = 1; i < s.length(); i++) {
	                CNode newNode = new CNode(s.charAt(i));
	                currNode.next = newNode;
	                newNode.prev = currNode;
	                currNode = newNode;
	                length++;
	            }
	            currNode.next = firstC;
	            firstC.prev = currNode;
	        } else {
	            CNode lastNode = firstC.prev;
	            for (int i = 0; i < s.length(); i++) {
	                CNode newNode = new CNode(s.charAt(i));
	                newNode.prev = lastNode;
	                newNode.next = firstC;
	                lastNode.next = newNode;
	                firstC.prev = newNode;
	                lastNode = newNode;
	                length++;
	            }
	            firstC.prev = lastNode;
	            lastNode.next = firstC;
	        }
	    }
	    return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		if (c != null && c.length > 0) {
	        if (firstC == null) {
	            firstC = new CNode(c[0]);
	            length = 1;
	            CNode currNode = firstC;
	            for (int i = 1; i < c.length; i++) {
	                CNode newNode = new CNode(c[i]);
	                currNode.next = newNode;
	                newNode.prev = currNode;
	                currNode = newNode;
	                length++;
	            }
	            currNode.next = firstC;
	            firstC.prev = currNode;
	        } else {
	            CNode currNode = firstC;
	            for (int i = 0; i < c.length; i++) {
	                CNode newNode = new CNode(c[i]);
	                CNode lastNode = firstC.prev;
	                lastNode.next = newNode;
	                newNode.prev = lastNode;
	                newNode.next = firstC;
	                firstC.prev = newNode;
	                lastNode = newNode;
	                length++;
	            }
	        }
	    }
	    return this;
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
	{
		if (firstC == null) {
	        firstC = new CNode(c);
	        length = 1;
	        firstC.next = firstC;
	        firstC.prev = firstC;
	    } else {
	        CNode newNode = new CNode(c);
	        CNode lastNode = firstC.prev;
	        lastNode.next = newNode;
	        newNode.prev = lastNode;
	        newNode.next = firstC;
	        firstC.prev = newNode;
	        lastNode = newNode;
	        length++;
	    }
	    return this;
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if (index < 0 || index >= length) {
	        throw new IndexOutOfBoundsException("Invalid index");
	    }
	    CNode currNode = firstC;
	    for (int i = 0; i < index; i++) {
	        currNode = currNode.next;
	    }
	    return currNode.data;
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder delete(int start, int end)
	{
		if (start < 0 || start >= length || end <= start) {
	        return this;
	    }
		
		// 如果起始索引为 0，且结束索引大于等于链表长度，表示需要删除整个链表中的字符
	    if (start == 0 && end >= length) {
	        firstC = null;
	        length = 0;
	        return this;
	    }
          
	    // 如果结束索引大于等于链表长度，将结束索引调整为链表长度，以确保不超出链表范围
	    if (end >= length) {
	        end = length;
	    }

	    CNode startNode = getNode(start);
	    CNode endNode = getNode(end - 1);

	    if (start == 0) {
	        firstC = endNode.next;
	        endNode.next.prev = null;  // 断开起始节点与后续节点的链接
	    } else {
	        startNode.prev.next = endNode.next;
	        if (endNode.next != null) {
	            endNode.next.prev = startNode.prev;
	        }
	    }

	    length -= (end - start);
	    return this;
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).  If "index"
	// is the last character in the MyStringBuilder, go backward in the list in
	// order to make this operation faster (since the last character is simply
	// the previous of the first character)
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		if (index < 0 || index >= length) {
	        return this;
	    }
		// 如果索引为零，则将 firstC 指向下一个节点，同时调整链表中前一个节点的指针，将其指向新的第一个节点
	    if (index == 0) {
	        firstC = firstC.next;
	        firstC.prev = firstC.prev.prev;  // 更新新的首节点的前一个节点，将其指向原首节点的前一个节点的前一个节点，即跳过原首节点的前一个节点
	        firstC.prev.next = firstC;  // 更新新的首节点的前一个节点的后一个节点，将其指向新的首节点，即连接新的首节点和原首节点之后的节点
	        length--;
	        return this;
	    }
	    // 如果索引为字符串长度减一，说明要删除的是末尾字符，直接调整链表中最后一个节点的指针，使其指向新的末尾节点
	    if (index == length - 1) {
	        firstC.prev = firstC.prev.prev;
	        firstC.prev.next = firstC;
	        length--;
	        return this;
	    }
	    // 找到指定索引位置的节点 currNode，然后修改其前一个节点和后一个节点的指针，将其连接起来，从而将 currNode 从链表中移除
	    CNode currNode = firstC;
	    for (int i = 0; i < index; i++) {
	        currNode = currNode.next;
	    }
	    currNode.prev.next = currNode.next;
	    currNode.next.prev = currNode.prev;
	    length--;
	    return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		if (str == null || str.length() == 0) {
	        return -1;
	    }
	    CNode currNode = firstC;
	    int index = 0;
	    while (index < length) {
	    	// 判断当前节点 currNode 的字符是否与字符串 str 的第一个字符相等。如果相等，说明可能找到了匹配的子串，进一步进行检查
	        if (currNode.data == str.charAt(0)) {
	            CNode node = currNode;
	            int i = 0;
	            while (i < str.length() && node.data == str.charAt(i)) {
	                node = node.next;
	                i++;
	            }
	            // 如果 i 的值等于字符串 str 的长度，表示找到了完全匹配的子串
	            if (i == str.length()) {
	                return index;
	            }
	        }
	        currNode = currNode.next;
	        index++;
	    }
	    return -1;
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		if (offset < 0 || offset > length || str == null) {
	        return this;
	    }

	    if (offset == length) {
	        return append(str);
	    }

	    CNode currNode = firstC;
	    // 通过循环将当前节点 currNode 移动到偏移量 offset 所在的节点位置
		for(int i = 0; i < offset; ++i) {
			currNode = currNode.next;
		}
		
		CNode prevNode = currNode.prev;
		int siz = str.length();
		for(int i = 0; i < siz; ++i) {
			CNode newNode = new CNode(str.charAt(i));  // 创建一个新的节点 newNode 来存储要插入的字符
			prevNode.next = newNode;  // 使用 prevNode 节点进行链接，在偏移量 offset 前插入了新的节点
			newNode.prev = prevNode;
			prevNode = newNode;
		}
		if(offset == 0) firstC = firstC.prev.next;
		prevNode.next = currNode;
		currNode.prev = prevNode;
	
		length += str.length();
		return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		if (offset < 0 || offset > length) {
	        return this;
	    }

	    if (offset == length) {
	        return append(c);
	    }

	    CNode currNode = firstC;
		for(int i = 0; i < offset; ++i) {
			currNode = currNode.next;
		}
		CNode newNode = new CNode(c);
		CNode prevNode = currNode.prev;
		prevNode.next = newNode;
		newNode.prev = prevNode;
		newNode.next = currNode;
		currNode.prev = newNode;
		
		++length;
		return this;
	}

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder replace(int start, int end, String str)
	{
		if (start < 0 || start >= length || end <= start || str == null) {
	        return this;
	    }

	    if (end > length) {
	        end = length;
	    }

	    end = Math.min(end, length);
		CNode currNode = firstC;
		// 通过循环将当前节点 currNode 移动到起始索引 start 所在的节点位置
		for(int i = 0; i < start; ++i) {
			currNode = currNode.next;
		}
		
		CNode prevNode = currNode.prev;
		for(int i = 0; i < str.length(); ++i) {
			CNode newNode = new CNode(str.charAt(i));  // 创建一个新的节点 newNode 来存储替换字符串中的字符
			prevNode.next = newNode;  // 用 prevNode 节点进行链接，将替换字符串插入到了起始索引 start 前的位置
			newNode.prev = prevNode;
			prevNode = newNode;
		}
		// 再次使用循环将 currNode 移动到终止索引 end 所在的节点位置
		for(int i = start; i < end; ++i) {
			currNode = currNode.next;
		}
		if(start == 0) {
			firstC = firstC.prev.next;
		}
		prevNode.next = currNode;
		currNode.prev = prevNode;
		length = length - (end - start) + str.length();
		
		return this;
	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder.  For this method
	// you should do the following:
	// 1) Create a character array of the appropriate length
	// 2) Fill the array with the proper characters from your MyStringBuilder
	// 3) Return a new String using the array as an argument, or
	//    return new String(charArray);
	public String substring(int start, int end)
	{
		if (start < 0 || start >= length || end <= start || end > length) {
	        return "";
	    }
	    char[] charArray = new char[end - start];
	    CNode currNode = getNode(start);
	    for (int i = 0; i < end - start; i++) {
	        charArray[i] = currNode.data;
	        currNode = currNode.next;
	    }
	    return new String(charArray);
	}

	// Return as a String the reverse of the contents of the MyStringBuilder.  Note
	// that this does NOT change the MyStringBuilder in any way.  See substring()
	// above for the basic approach.
	public String revString()
	{
		char[] reversed = new char[length];
	    CNode currNode = firstC;
	    int i = length - 1;

	    while (i >= 0) {
	        reversed[i] = currNode.data;
	        i--;
	        currNode = currNode.next;
	    }

	    return new String(reversed);
	}
	
	private CNode getNode(int index) {
	    CNode currNode = firstC;
	    for (int i = 0; i < index; i++) {
	        currNode = currNode.next;
	    }
	    return currNode;
	}
	
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data, next and prev fields directly.
	private class CNode
	{
		private char data;
		private CNode next, prev;

		public CNode(char c)
		{
			data = c;
			next = null;
			prev = null;
		}

		public CNode(char c, CNode n, CNode p)
		{
			data = c;
			next = n;
			prev = p;
		}
	}
}