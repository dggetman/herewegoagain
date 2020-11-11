package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Removable, Insertable, Serializable {

    private static final long serialVersionUID = -7114814848443532576L;
    private Node head;
    private int count = 0;

    protected static class Node implements Serializable {
        private static final long serialVersionUID = -1953474641328267501L;
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
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Less than minimum length");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Less than minimum length");
        }
        if ((xFrom >= xTo)) {
            throw new IllegalArgumentException("Incorrect parameter values");
        }
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
    public int floorIndexOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("X is less than the left border");
        }
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
    public double extrapolateLeft(double x) {
        if (head.x == head.prev.x) {
            return head.y;
        }
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    public double extrapolateRight(double x) {
        if (head.x == head.prev.x) {
            return head.y;
        }
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        Node leftNode = getNode(floorIndex);
        Node rightNode = leftNode.next;
        if (x < leftNode.x || rightNode.x > x) {
            throw new InterpolationException("X is out of bounds of interpolation");
        }
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }

    @Override
    public void insert(double x, double y) {
        if (count == 0) {
            addNode(x, y);
        } else if (indexOfX(x) != -1) {
            setY(indexOfX(x), y);
        } else {
            int index = floorIndexOfX(x);
            Node newNode = new Node();
            newNode.x = x;
            newNode.y = y;
            newNode.next = head;
            newNode.prev = head.prev;
            head.prev.next = newNode;
            if (index == 0) {
                head = newNode;
            } else {
                head.prev = newNode;
            }
            count++;
        }
    }

    @Override
    public void remove(int index) {
        Node deletedNode = getNode(index);
        deletedNode.prev.next = deletedNode.next;
        deletedNode.next.prev = deletedNode.prev;
        count--;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private Node node = head;

            public boolean hasNext() {
                return node != null;
            }

            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(node.x, node.y);
                node = (node != head.prev) ? node.next : null;
                return point;
            }
        };
    }
}