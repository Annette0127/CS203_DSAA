import java.util.*;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int AorP;
        long character;
        BST adopter = new BST();
        BST pet = new BST();
        long result = 0;
        BST.Node s, p;
        for (int i = 0; i < number; i++) {
            AorP = sc.nextInt();
            character = sc.nextLong();
            if (AorP == 0) {//pet coming
                if (adopter.root == null) {
                    pet.Insert(character);
                } else {
                    s = adopter.findSuccessor(character);
                    p = adopter.findPredecessor(character);
                    if (s != null && p != null) {
                        if (Math.abs(s.value - character) >= Math.abs(p.value - character)) {
                            result += Math.abs(p.value - character);
                            adopter.delete(p.value);
                        } else if (Math.abs(s.value - character) < Math.abs(p.value - character)) {
                            result += Math.abs(s.value - character);
                            adopter.delete(s.value);
                        }
                    } else if (s != null) {
                        result += Math.abs(s.value - character);
                        adopter.delete(s.value);
                    } else if (p != null) {
                        result += Math.abs(p.value - character);
                        adopter.delete(p.value);
                    } else {
                    }
                }
            } else {
                if (pet.root == null) {
                    adopter.Insert(character);
                } else {
                    s = pet.findSuccessor(character);
                    p = pet.findPredecessor(character);

                    if (s != null && p != null) {
                        if (Math.abs(s.value - character) >= Math.abs(p.value - character)) {
                            result += Math.abs(p.value - character);
                            pet.delete(p.value);
                        } else if (Math.abs(s.value - character) < Math.abs(p.value - character)) {
                            result += Math.abs(s.value - character);
                            pet.delete(s.value);
                        }
                    } else if (s != null) {
                        result += Math.abs(s.value - character);
                        pet.delete(s.value);

                    } else if (p != null) {
                        result += Math.abs(p.value - character);
                        pet.delete(p.value);
                    } else {
                    }
                }
            }
        }
        System.out.println(result);
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
                if (n == root) {
                    root = null;
                    return;
                } else if (n.parent.left == n) n.parent.left = null;
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
            height(root);
            bal(root);
            siz(root);
            if (Math.abs(root.balanceFactor) > 1) balance(root);
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

    public Node findSuccessor(long n) {
        Node successor = null, pointer;
        if (root.value == n) return root;
        else {
            pointer = root;
            while (true) {
                if (pointer.value < n) {
                    if (pointer.right != null) pointer = pointer.right;
                    else return successor;
                } else if (pointer.value == n) return pointer;
                else {
                    if (pointer.left != null) {
                        successor = pointer;
                        pointer = pointer.left;
                    } else return pointer;
                }
            }
        }
    }

    public Node findPredecessor(long n) {
        Node predecessor = null, pointer;
        if (root.value == n) return root;
        else {
            pointer = root;
            while (true) {
                if (pointer.value > n) {
                    if (pointer.left != null) pointer = pointer.left;
                    else return predecessor;
                } else if (pointer.value == n) return pointer;
                else {
                    if (pointer.right != null) {
                        predecessor = pointer;
                        pointer = pointer.right;
                    } else return pointer;
                }
            }
        }
    }


    public Node findPredecessor(Node n) {
        if (n.left != null) {
            Node pointer = n;
            while (n.right != null) pointer = pointer.right;
            return pointer;
        } else return n;
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
}