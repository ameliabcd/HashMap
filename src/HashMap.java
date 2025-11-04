import java.util.LinkedList;

public class HashMap<K,V> {
    int capacity;
    double threshold;
    private Node<K,V>[] table;
    private int size;
    private K[] keyList;

    public HashMap(int c,double t)
    {
        capacity=c;
        threshold=t;
    }

    public HashMap(int c)
    {
        capacity=c;
        threshold=0.75;
        table=new Node[c];
    }

    public HashMap()
    {
        capacity=12;
        threshold=0.75;
        table=new Node[12];
    }


    public V put(K key, V value)
    {

        Node<K,V> current=new Node<>(key,value);
        if(table[current.hash] == null)
        {
            table[current.hash]=current;
            table[current.hash].head=current;
        }
        else
        {
            table[current.hash].previous.setNext(current);
        }
        table[current.hash].previous=current;
        size++;
        return current.getValue();
    }

    public V get(K key)
    {
        int index=key.hashCode() & (table.length-1);
        if(table[index]==null)
        {
            return null;
        }
        else
        {
            Node<K,V> trace=table[index];
            while(trace.getNext()!=null)
            {
                if(trace.getKey().equals(key))
                    return trace.getValue();
                trace=trace.getNext();
            }
        }
        return null;
    }

    public V remove(K key)
    {
        int index=key.hashCode() & (table.length-1);
        if(!this.containsKey(key))
        {
            return null;
        }
        else
        {
            Node<K,V> trace=table[index].head;
            V get;
            if(trace.getKey().equals(key))
            {
                get=trace.getNext().getValue();
                table[key.hashCode()].head=trace.getNext();
                return get;
            }
            while(trace.getNext()!=null)
            {
                if(trace.getNext().getKey().equals(key))
                {
                    get=trace.getNext().getValue();
                    if(trace.getNext().getNext()==null)
                    {
                        trace.setNext(null);
                        return get;
                    }
                    trace.setNext(trace.getNext().getNext());
                    return get;
                }
                trace=trace.getNext();
            }
        }
        return null;
    }

    public boolean containsKey(K key)
    {
        int index=key.hashCode() & (table.length-1);
        if(table[index]==null)
        {
            return false;
        }
        else
        {
            Node<K,V> trace=table[index].head;
            while(trace.getNext()!=null)
            {
                if(trace.getKey().equals(key))
                    return true;
                trace=trace.getNext();
            }
        }
        return false;
    }

    public boolean containsValue(V value)
    {
        for(int i=0;i<table.length;i++)
        {
            Node<K,V> trace=table[i].head;
            while(trace.getNext()!=null)
            {
                if(trace.getValue().equals(value))
                    return true;
                trace=trace.getNext();
            }
        }
        return false;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        for (Node<K, V> kvNode : table) {
            if (kvNode != null) {
                return false;
            }
        }
        return true;

    }

    public void clear()
    {
        for(int i=0;i<table.length;i++)
        {
            table[i].head=null;
        }
    }

    public V getOrDefault(K key, V defaultValue)
    {
        return null;
    }

    public V putIfAbsent(K key, V value)
    {
        return null;
    }

    public boolean remove(Object key, Object value)
    {
        return false;
    }

    public V replace(K key, V value)
    {
        return null;
    }

    public boolean replace(K key, V oldValue, V newValue)
    {
        return false;
    }







}
