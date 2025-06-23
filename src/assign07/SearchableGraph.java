package assign07;

import java.util.List;

public interface SearchableGraph<T> {
    boolean depthFirstSearch(T source, T destination);
    List<T> breadthFirstSearch(T source, T destination);
    List<T> topoSort();
}
