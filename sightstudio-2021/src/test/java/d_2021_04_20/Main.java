package d_2021_04_20;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class ListNode {

    long data;
    ListNode prev;
    ListNode next;

    public ListNode()
    {
        data = 0;
        prev = this;
        next = this;
    }

    public static ListNode appendListNode(ListNode head, long data)
    {
        ListNode node = new ListNode();
        node.data = data;
        if (head == null)
        {
            head = node;
        }
        else
        {
            ListNode last = head.prev;
            last.next = node;
            head.prev = node;
            node.prev = last;
            node.next = head;
        }
        return head;
    }

    public static ListNode removeListNode(ListNode head, ListNode node)
    {
        if (head == head.next)
        {
            return null;
        }
        else
        {
            ListNode prevNode = node.prev;
            ListNode nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            return (head == node) ? nextNode : head;
        }

    }
}

public class Main {

    static String[] cal = new String[100001];
    static long[]   add = new long[100001];
    static int idx, numidx;
    static int a, b;

    static long error = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        idx = 0;
        numidx = 0;
        a = b = 0;

        while(true) {

            String[] input = br.readLine().split(" ");

            if(input[0].equals("QUIT")) {
                break;
            }

            if(input[0].equals("END")) {
                int N = Integer.parseInt(br.readLine());

                for (int i = 0; i < N; i++) {
                    long res = solution(Long.parseLong(br.readLine()));

                    if(res == error)
                        System.out.println("ERROR");
                    else
                        System.out.println(res);
                }

            }
            else if(input[0].equals("")) {
                idx = numidx = 0;
                System.out.println();
            }
            else {
                cal[idx++] = input[0];
                a = idx;

                if(input[0].equals("NUM")) {
                    add[numidx++] = Integer.parseInt(input[1]);
                    b = numidx;
                }
            }

        }
    }

    public static long solution(long num) {

        ListNode head = null;
        head = ListNode.appendListNode(head, num);

        idx = numidx = 0;
        while(idx < a && numidx <= b) {

            if(cal[idx].equals("NUM")) {
                long number = add[numidx++];
                head = ListNode.appendListNode(head, number);

            } else if(cal[idx].equals("POP")) {
                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

            } else if(cal[idx].equals("INV")) {
                long number = head.prev.data;
                head.prev.data = -number;

            } else if(cal[idx].equals("DUP")) {
                long number = head.prev.data;
                ListNode.appendListNode(head, number);

            } else if(cal[idx].equals("SWP")) {

                long top = head.prev.data;
                long second = head.prev.prev.data;

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                head = ListNode.appendListNode(head, top);
                head = ListNode.appendListNode(head, second);

            } else if(cal[idx].equals("ADD")) {
                long top = head.prev.data;
                long second = head.prev.prev.data;
                long sum = top + second;

                if(sum > 1000000000 || sum < -1000000000)
                    return error;

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                head = ListNode.appendListNode(head, sum);

            } else if(cal[idx].equals("SUB")) {
                long top = head.prev.data;
                long second = head.prev.prev.data;
                long sum = second - top;

                if(sum > 1000000000 || sum < -1000000000)
                    return error;

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                head = ListNode.appendListNode(head, sum);
            } else if(cal[idx].equals("MUL")) {
                long top = head.prev.data;
                long second = head.prev.prev.data;
                long sum = top * second;

                if(sum > 1000000000 || sum < -1000000000)
                    return error;

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                head = ListNode.appendListNode(head, sum);
            } else if(cal[idx].equals("DIV")) {

                long top = head.prev.data;
                if(top == 0) return error;
                long second = head.prev.prev.data;
                long sum = Math.abs(second) / Math.abs(top);

                int minusCnt = 0;
                if(top < 0) minusCnt++;
                if(second < 0) minusCnt++;

                if(minusCnt == 1) {
                    sum = -sum;
                }

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                head = ListNode.appendListNode(head, sum);

            } else if(cal[idx].equals("MOD")) {

                long top = head.prev.data;
                if(top == 0) return error;
                long second = head.prev.prev.data;
                long sum = Math.abs(second) % Math.abs(top);

                if(second < 0) {
                    sum = -sum;
                }

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                if(head != null)
                    head = ListNode.removeListNode(head, head.prev);
                else
                    return error;

                head = ListNode.appendListNode(head, sum);

            }
            idx++;
        }

        int count = 0;
        long data = 0;

        while(head != null) {
            data = head.data;
            head = ListNode.removeListNode(head, head.prev);
            count++;
        }

        if(count == 1) {
            return data;
        }
        else {
            return error;
        }
    }
}
