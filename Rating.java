package FinalProject;
import java.util.*;
public interface Rating<E> {
	public E pickMax(Comparator<E> c);
    public E pickMin(Comparator<E> c);
    public void add(E e);
    public boolean isEmpty();
    public int size();
}
