package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

    private Node head;
    private int count = 0;

    protected static class Node {
        public double x;
        public double y;
        public Node next;
        public Node prev;
    }

    public void addNode(double x, double y) {
        Node newNode = new Node();
        newNode.x = x;
        newNode.y = y;
        if (head == null) {
            head = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            Node last = head.prev;
            newNode.prev = last;
            newNode.next = head;
            head.prev = newNode;
            last.next = newNode;
        }
        count += 1;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        this.count = count;
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            this.addNode(xFrom, source.apply(xFrom));
            xFrom += step;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    private Node getNode(int index) {
        Node indexNode;
        if (index <= (count / 2)) {
            indexNode = head;
            for (int i = 0; i < count; i++) {
                if (i == index) {
                    return indexNode;
                } else {
                    indexNode = indexNode.next;
                }
            }
        } else {
            indexNode = head.prev;
            for (int i = count - 1; i > 0; i--) {
                if (i == index) {
                    return indexNode;
                }
                indexNode = indexNode.prev;
            }
        }
        return null;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double valueY) {
        getNode(index).y = valueY;
    }

    @Override
    public int indexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x == x) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    public int indexOfY(double y) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.y == y) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x < x) {
                indexNode = indexNode.next;
            } else {
                if (i == 0) {
                    return 0;
                }
                return i - 1;
            }
        }
        return getCount();
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (head.x == head.prev.x) {
            return head.y;
        }
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (head.x == head.prev.x) {
            return head.y;
        }
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (head.x == head.prev.x) {
            return head.y;
        }
        Node leftNode = getNode(floorIndex);
        Node rightNode = leftNode.next;
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }
}