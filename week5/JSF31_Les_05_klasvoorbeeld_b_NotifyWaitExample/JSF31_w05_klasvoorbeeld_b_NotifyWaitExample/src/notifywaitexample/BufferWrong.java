package notifywaitexample;

class BufferWrong implements Buffer {

    private int n;

    @Override
    public synchronized int get() {
        System.out.println("Got: " + n);
        return n;
    }

    @Override
    public synchronized void put(int n) {
        this.n = n;
        System.out.println("Put: " + n);
    }
}
