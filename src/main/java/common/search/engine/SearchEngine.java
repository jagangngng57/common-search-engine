package common.search.engine;

public interface SearchEngine {
    void visit(String url);
    void search(String searchTerm);
    String getFirstResult();
}
