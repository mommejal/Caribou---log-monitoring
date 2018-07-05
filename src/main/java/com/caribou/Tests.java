package com.caribou;

import java.util.ArrayDeque;
import java.util.Queue;

public class Tests {

	public static void main(String[] args) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		System.out.println(queue.toString());
		queue.add(1);
		System.out.println(queue.toString());
		queue.add(2);
		System.out.println(queue.toString());
		queue.add(3);
		System.out.println(queue.toString());
		queue.poll();
		System.out.println(queue.toString());
		queue.add(4);
		System.out.println(queue.toString());
		queue.poll();
		System.out.println(queue.toString());
	}
}
