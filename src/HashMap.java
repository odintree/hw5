public class HashMap {

    private static final int SIZE = 16;
    private Entry table[] = new Entry[SIZE];

    class Entry {
        Book key;
        String value;
        Entry next;

        Entry(Book k, String v) {
            key = k;
            value = v;
        }

        public Book getKey() {
            return key;
        }
    }

    static class Book {

        private Integer id;
        private String name;

        Book(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int hashCode(){
            return id % 10;
        }

        @Override
        public boolean equals(Object obj) {
            Book otherBook = (Book) obj;
            return this.name.equals(otherBook.name);
        }

        @Override
        public String toString() {
            return this.id + "-" + name;
        }
    }


    private int getSuppHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int getBucketNum(int hash) {
        return hash & (SIZE - 1);
    }

    public void put(Book key, String value) {
        int userHash = key.hashCode();
        int hash = getSuppHash(userHash);
        int bucket = getBucketNum(hash);
        Entry existing = table[bucket];
        for (; existing != null; existing = existing.next) {
            if (existing.key.equals(key)) {
                System.out.println("replacing existing key "
                                + key + ", with value "
                                + value);
                existing.value = value;
                return;
            } else {
                System.out.println("Collision detected with " + key);
            }
        }

        System.out.println("Put the key:" + key + ", with value:" + value + " to the list");
        Entry oldBucket = new Entry(key, value);
        oldBucket.next = table[bucket];
        table[bucket] = oldBucket;
    }

    public Entry get(Book key) {
        int hash = getSuppHash(key.hashCode());
        int bucket = getBucketNum(hash);
        Entry existing = table[bucket];
        while (existing != null) {
            System.out.println("Traversing the list inside the bucket for the key " + existing.getKey());
            if (existing.key.equals(key)) {
                return existing;
            }
            existing = existing.next;
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap tmhm = new HashMap();
    }



}