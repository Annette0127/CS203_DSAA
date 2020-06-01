import java.util.*;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), k = sc.nextInt();
        long[] a = new long[m];
        for (int i = 0; i < m; i++) a[i] = sc.nextInt();
        BST avl = new BST();
        avl.root = new BST.Node(a[0]);
        for (int i = 1; i < k; i++) avl.Insert(a[i]);
        for (int i = 0; i < m - k + 1; i++) {
            BST.value = 0;
            avl.preOrder(avl.root, sc.nextInt());
            System.out.println(BST.value);
            if (k + i == m) break;
            avl.delete(a[i]);
            avl.Insert(a[k + i]);
        }
    }
}

class BST {
    Node root;

    static class Node {
        Node left, right, parent;
        long balanceFactor, height;
        long value;
        long number;
        long size = 1;

        Node(long e) {
            value = e;
        }
    }

//    Node sortedArrayToBBST(long[] arr, int start, int end) {
//        if (start > end) {
//            return null;
//        }
//        int mid = start + (end - start) / 2;
//        Node node = new Node(arr[mid]);
//        node.left = sortedArrayToBBST(arr, start, mid - 1);
//        node.right = sortedArrayToBBST(arr, mid + 1, end);
//        return node;
//    }

//    public static long min(long a, long b) {
//        return a > b ? b : a;
//    }

    public static long max(long a, long b) {
        return a < b ? b : a;
    }

    public void height(Node c) {
        if (c.left != null && c.right != null) c.height = max(c.left.height, c.right.height) + 1;
        else if (c.right != null) c.height = c.right.height + 1;
        else if (c.left != null) c.height = c.left.height + 1;
        else c.height = 0;
    }

    public void bal(Node n) {
        if (n.left != null && n.right != null) n.balanceFactor = n.right.height - n.left.height;
        else if (n.right != null) n.balanceFactor = n.right.height + 1;
        else if (n.left != null) n.balanceFactor = -n.left.height - 1;
        else n.balanceFactor = 0;
    }

    public void siz(Node n) {
        if (n.left != null && n.right != null) n.size = n.right.size + n.left.size + 1;
        else if (n.right != null) n.size = n.right.size + 1;
        else if (n.left != null) n.size = n.left.size + 1;
        else n.size = 1;
    }

    public void Insert(long value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node n = root;
        while (true) {
            if (n.value > value) {
                if (n.left != null) n = n.left;
                else break;
            } else if (n.value < value) {
                if (n.right != null) n = n.right;
                else break;
            } else {
                n.number++;
                return;
            }
        }
        Node newNode = new Node(value);
        if (n.value > value) n.left = newNode;
        else n.right = newNode;
        newNode.parent = n;
        n.size++;
        height(n);
        bal(n);
        while (n != root) {
            n = n.parent;
            height(n);
            bal(n);
            n.size++;
            if (Math.abs(n.balanceFactor) > 1) {
                balance(n);
                break;
            }
        }
//        check(root);
//        System.out.println("---");
    }

    public void balance(Node c) {
        if (c.balanceFactor == -2 && c.left.balanceFactor <= 0) rotateRight(c);
        else if (c.balanceFactor == 2 && c.right.balanceFactor >= 0) rotateLeft(c);
        else if (c.balanceFactor == -2 && c.left.balanceFactor == 1) rotateLeftRight(c);
        else if (c.balanceFactor == 2 && c.right.balanceFactor == -1) rotateRightLeft(c);
//        c = c.parent;
        while (c != root) {
            c = c.parent;
            height(c);
            bal(c);
            siz(c);
        }
    }

    public void rotateLeft(Node c) {
        Node r = c.right;
        c.right = r.left;
        if (r.left != null) r.left.parent = c;
        r.left = c;
        if (c != root) {
            if (c == c.parent.left) c.parent.left = r;
            else c.parent.right = r;
            r.parent = c.parent;
        } else root = r;
        c.parent = r;
        height(c);
        height(r);
        bal(c);
        bal(r);
        siz(c);
        siz(r);
    }

    public void rotateRight(Node c) {
        Node l = c.left;
        c.left = l.right;
        if (l.right != null) l.right.parent = c;
        l.right = c;
        if (c != root) {
            if (c == c.parent.left) c.parent.left = l;
            else c.parent.right = l;
            l.parent = c.parent;
        } else root = l;
        c.parent = l;
        height(c);
        height(l);
        bal(c);
        bal(l);
        siz(c);
//        check(root);
//        System.out.println("----");
        siz(l);
//        check(root);
//        System.out.println("-----");
    }

    public void rotateRightLeft(Node c) {
        rotateRight(c.right);
        rotateLeft(c);
    }

    public void rotateLeftRight(Node c) {
        rotateLeft(c.left);
        rotateRight(c);
    }

    public Node find(long value) {
        if (root == null) return null;
        else {
            Node pointer = root;
            while (true) {
                if (value < pointer.value) {
                    if (pointer.left != null) pointer = pointer.left;
                    else return null;
                } else if (value > pointer.value) {
                    if (pointer.right != null) pointer = pointer.right;
                    else return null;
                } else return pointer;
            }
        }
    }

    public void delete(long value) {
        Node n = find(value);
        if (n == null) return;
        if (n.number > 0) {
            n.number--;
            return;
        }
        if (n.right == null) {
            if (n.left == null) {
                if (n == root) root = null;
                else if (n.parent.left == n) n.parent.left = null;
                else n.parent.right = null;
            } else {
                if (n == root) root = n.left;
                else {
                    if (n.parent.left == n) n.parent.left = n.left;
                    else n.parent.right = n.left;
                    n.left.parent = n.parent;
                }
                n = n.left;
            }
            while (n != root) {
                n = n.parent;
                height(n);
                bal(n);
                n.size--;
                if (Math.abs(n.balanceFactor) > 1) {
                    balance(n);
                    break;
                }
            }
//            height(root);
//            bal(root);
//            siz(root);
//            if (Math.abs(root.balanceFactor) > 1) balance(root);
        } else {
            Node successor = findSuccessorToDelete(n);
            exchange(n, successor);
            if (successor.right == null) {
                if (successor.parent.left == successor) successor.parent.left = null;
                else successor.parent.right = null;
            } else {
                if (successor.parent.left == successor) {
                    successor.parent.left = successor.right;
                    successor.right.parent = successor.parent;
                } else {
                    successor.parent.right = successor.right;
                    successor.right.parent = successor.parent;
                }
            }
            while (successor != root) {
                successor = successor.parent;
                height(successor);
                bal(successor);
                successor.size--;
                if (Math.abs(successor.balanceFactor) > 1) {
                    balance(successor);
                    break;
                }
            }
        }
    }

    public Node findSuccessorToDelete(Node n) {
        if (n.right != null) {
            Node pointer = n.right;
            while (pointer.left != null) pointer = pointer.left;
            return pointer;
        } else return null;
    }

    public Node findSuccessor(Node n) {
        if (n.right != null) {
            Node pointer = n;
            while (n.left != null) pointer = pointer.left;
            return pointer;
        } else {
            Node pointer = n;
            while (pointer.parent != null && pointer.parent.left != pointer) pointer = pointer.parent;
            return pointer;
        }
    }

    public Node findPredecessor(Node n) {
        if (n.left != null) {
            Node pointer = n;
            while (n.right != null) pointer = pointer.right;
            return pointer;
        } else return null;
    }

    public Node exchange(Node a, Node b) {//b is the leaf Node
        if (a == null || b == null) return null;
        long tmp;
        tmp = a.value;
        a.value = b.value;
        b.value = tmp;
        return b;
    }

    public void check(Node node) {
        if (node == null) return;
        check(node.left);
        System.out.println(node.value + " " + node.balanceFactor + " " + node.height + " " + node.size);
        check(node.right);
    }

    public static long value;

    void preOrder(Node node, long n) {
        if (node == null) return;
        if (node.left != null) {
            if (node.left.size >= n) preOrder(node.left, n);
            else if (node.left.size == n - 1) value = node.value;
            else if (node.size >= n) preOrder(node.right, n - node.left.size - 1);
        } else {
            if (n == 1) value = node.value;
            else if (node.size >= n) preOrder(node.right, n - 1);
        }
    }
//        boolean left = true;
//        Node cross = root;
//        if (left) {
//            if (node.left != null) {
//                cross = node;
//                node = node.left;
//            } else node = node.parent;
//        }
//        if (node == cross) {
//            node = node.right;
//        }
}