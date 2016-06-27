/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        if(list.size() == 1 || list.size() == 0)
			return list;
		int mid=list.size()/2;
		List<T> left=list.subList(0,mid);
		List<T> right=list.subList(mid,list.size());
		//System.out.println(left.toString());
		//System.out.println(right.toString());
		return merge(mergeSort(left,comparator),mergeSort(right, comparator),comparator);
		
	}
	
	private List<T> merge(List<T> left, List<T> right, Comparator<T> comp){
		if(left.size() == 0)
			return right;
		if(right.size()== 0)
			return left;
		//System.out.println(left.toString());
		//System.out.println(right.toString());
		List<T> result=new ArrayList<T>();
		int indL=0;
		int indR=0;
		while(indL < left.size()){
			if(indR >= right.size()){
				result.addAll(left.subList(indL,left.size()));
				break;
			} 
			if(comp.compare(left.get(indL),right.get(indR)) <=0){
				result.add(left.get(indL));
				indL++;
			}else{
				result.add(right.get(indR));
				indR++;
			}
		}
		if(indR < right.size()){
			result.addAll(right.subList(indR,right.size()));
		}
		System.out.println(result.toString());
		return result;
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
		PriorityQueue<T> queue=new PriorityQueue<T>();
		for(T ele: list){
			queue.offer(ele);
		}
		list.clear();
		while(!queue.isEmpty()){
			list.add(queue.poll());
		}
		
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        insertionSort(list,comparator);
		return list.subList(list.size()-k,list.size());
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}