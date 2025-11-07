public class Node<K,V> {
    K key;
    V value;
    int hash;
    Node<K,V> head;
    Node<K,V> next;
    Node<K,V> tail;
    public Node(K k,V v)
    {
        key=k;
        value=v;
        hash=key.hashCode();
    }

    public void setNext(Node<K,V> next) {
        this.next = next;
    }

    public Node<K,V> getNext() {
        return next;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
       this.value=value;
    }

    public K getKey() {
        return key;
    }
}
