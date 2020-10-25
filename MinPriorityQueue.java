package utils;

@SuppressWarnings("unchecked")
public class MinPriorityQueue<T extends Comparable<T>> {
  private int size;
  private Object[] array;
  public MinPriorityQueue() {
    size = 0;
    array = new Object[4];
  }

  public T remove() {
    Object holder = array[1];
    array[1] = array[size];
    array[size] = null;
    size--;

    T newRoot = (T) array[1];

    int currentIndex = 1;
    int smallestChildIndex = getIndexOfSmallestValidChild(currentIndex);

    while (smallestChildIndex != -1 && newRoot.compareTo((T)array[smallestChildIndex])>0) {
      array[currentIndex] = array[smallestChildIndex];
      currentIndex = smallestChildIndex;
      smallestChildIndex = getIndexOfSmallestValidChild(currentIndex);
    }
    array[currentIndex] = newRoot;
    return (T)holder;
  }

  private int getIndexOfSmallestValidChild(int currentIndex) {
    int leftChildIndex = currentIndex*2;
    int rightChildIndex = leftChildIndex+1;

    if (leftChildIndex>size) {
      return -1;
    }
    else if (leftChildIndex==size) {
      return leftChildIndex;
    }

    if ( ((T)array[leftChildIndex]).compareTo( (T)array[rightChildIndex]) > 0) {
      return rightChildIndex;
    }
    return leftChildIndex;
  }


  public void add(T elem) {

    if (++size == array.length-1) {
      increaseSize();
    }

    array[size] = elem;
    int currentIndex = size;
    int parentIndex = getParentIndex(currentIndex);
    while (currentIndex != 0 && parentIndex != 0 && elem.compareTo((T)array[parentIndex]) <= 0 ) {
      array[currentIndex] = array[parentIndex];
      currentIndex = parentIndex;
      parentIndex = getParentIndex(currentIndex);
    }
    array[currentIndex] = elem;
  }

  private int getParentIndex(int childIndex){
    return childIndex / 2;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private void increaseSize() {
    Object[] newArray = new Object[array.length*2];
    newArray[0] = null;
    if (array.length - 1 >= 0) System.arraycopy(array, 1, newArray, 1, array.length - 1);
    array = newArray;
  }

  public int getLength() {
    return array.length;
  }

  public int size() {
    return size;
  }
}


