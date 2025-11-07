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
        int n=1;
        while (n < capacity) { //change the capacity to the power of 2
            n = n * 2;
        }
        capacity=n;

        table=new Node[capacity];
    }

    public HashMap(int c)
    {
        int n = 1;
        capacity=c;
        while (n < capacity) {
            n = n * 2;
        }
        capacity=n;
        threshold=0.75;
        table=new Node[capacity];
    }

    public HashMap()
    {
        capacity=16;
        threshold=0.75;
        table=new Node[16];
    }



    public Node<K,V> getNode(K key)
    {
        if(this.containsKey(key))
        {
            int index=key.hashCode() & (table.length-1); //hashcode conversion
            Node<K,V> trace=table[index].head;
            while(trace!=null) //trace through linkedlist
            {
                if(trace.getKey().equals(key))
                {
                    return trace;
                }
                trace=trace.getNext();
            }
        }
        return null;
    }

    public void resize()
    {
        Node<K,V>[] oldTable=table;  // Save reference to old table
        capacity = capacity*2;       // Update capacity
        table= new Node[capacity];    // Create new table and assign it NOW
        size = 0;                      // Reset size

        for(int i = 0; i < oldTable.length; i++)  // Iterate old table
        {
            if(oldTable[i] != null)
            {
                Node<K, V> trace = oldTable[i].head;
                while (trace != null)  // Simplified loop
                {
                    this.put(trace.getKey(), trace.getValue());  // Now puts into new table
                    trace = trace.getNext();
                }
            }
        }
    }


    public V put(K key, V value)
    {
        if(this.containsKey(key)) //if key already exist
        {
            V val=this.getNode(key).getValue();
            this.getNode(key).setValue(value);
            return val;
        }

        int index=key.hashCode() & (table.length-1); //if key doesn't already exist
        Node<K,V> current=new Node<>(key,value);
        if(table[index] == null) //if the linkedlist is empty
        {
            table[index]=current;
            table[index].head=current;
        }
        else //when the linked list is not empty
        {
            table[index].tail.setNext(current);
        }
        table[index].tail =current;
        size++;

        if(this.size()>capacity*threshold) //when the size is too big
        {
            this.resize();
        }

        return null;
    }

    public V get(K key)
    {
        int index=key.hashCode() & (table.length-1);
        return getNode(key).getValue();
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
            size--;
            Node<K,V> trace=table[index].head;
            V get;
            if(trace.getKey().equals(key)) //if removing the head
            {
                get=trace.getValue();
                table[index].head=trace.getNext();
                if(table[index].head == null)
                {
                    table[index] = null;
                }
                return get;
            }
            while(trace.getNext()!=null) //removing the middle
            {
                if(trace.getNext().getKey().equals(key))
                {
                    get=trace.getNext().getValue();
                    if(trace.getNext().getNext()==null) //removing the end
                    {
                        trace.setNext(null);
                        table[index].tail=trace;
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
            while(trace!=null)//trace through every node in linked list
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
            if(table[i]!=null) //check if table[i] is null
            {
                Node<K, V> trace = table[i].head;
                while (trace!= null) { //goes through the whole linkedlist
                    if (trace.getValue().equals(value))
                        return true;
                    trace = trace.getNext();
                }
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
            if(table[i]!=null)
            {
                table[i].head=null;
                table[i]=null;
                table[i].tail=null;
            }
        }
        size=0;
    }

    public V getOrDefault(K key, V defaultValue)
    {
        if(!this.containsKey(key))
        {
            return defaultValue;
        }
        else
        {
            return this.get(key);
        }
    }

    public V putIfAbsent(K key, V value)
    {
        if(this.containsKey(key))
        {
            return this.get(key);
        }
        else
        {
            this.put(key,value);
            return null;

        }
    }

    public boolean remove(K key, V value)
    {
        if(this.containsKey(key))
        {
            if(this.get(key).equals(value))
            {
                this.remove(key);
                return true;
            }
        }
        return false;
    }

    public V replace(K key, V value)
    {
        if(this.containsKey(key))
        {
            V val;
            int index=key.hashCode() & (table.length-1);
            Node<K,V> trace=table[index].head;
            while(trace!=null)
            {
                if(trace.getKey().equals(key)) //trace through the whole linked list
                {
                    val= trace.getValue();
                    trace.setValue(value);
                    return val;
                }
                trace=trace.getNext();
            }

        }
        return null;
    }

    public boolean replace(K key, V oldValue, V newValue)
    {
        if(this.containsKey(key))
        {
            if(this.get(key).equals(oldValue))
            {
                V val;
                int index=key.hashCode() & (table.length-1);
                Node<K,V> trace=table[index].head;
                while(trace!=null) //trace through the linked list
                {
                    if(trace.getKey().equals(key))
                    {
                        trace.setValue(newValue);
                        return true;
                    }
                    trace=trace.getNext();
                }
            }
        }
        return false;
    }



}
