package ch11_advanced_data;

/*
    TODO: Java 에서 Tries 구현하기
        (1). 각 Tries 노드는 끝을 나타내는 endOfWord 와 children 을 member 로 가진다.
        (2). 이때 children 의 크기는 원하는 문자 집합의 크기만큼 할당한다.
        (3). Insert
            - Char 자리가 null 일경우 새로운 Node 할당.
            - Word 길이만큼 아래로 내려가며 반복.
        (4). Search
            - Char 자리가 null 일경우 return false.
            - Word 길이만큼 아래로 내려갔다면, isEndOfWord 로 완전한 단어 파악.
        (5). AutoComplete
            - 주어진 접두사의 최대 깊이만큼 이동.
            - Null 이 아닌 Children 에 대해 DFS 방식으로 탐색.
            - 만약 단어가 완성될 경우 출력.
 */

class TriesNode{
    TriesNode[] children = new TriesNode[26];
    Boolean isEndOfWord = false;
}

class Tries{
    TriesNode root;

    public Tries(){
        root = new TriesNode();
    }

    public void insert(String word){
        TriesNode node = root;
        char[] chars = word.toCharArray();
        for(char c : chars){
            if(node.children[c-'a'] == null){
                node.children[c-'a'] = new TriesNode();
            }
            node = node.children[c-'a'];
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word){
        TriesNode node = root;
        char[] chars = word.toCharArray();
        for(char c : chars){
            if(node.children[c-'a'] == null){
                return false;
            }
            node = node.children[c-'a'];
        }
        return node.isEndOfWord;
    }

    public void autoComplete(String prefix){
        TriesNode node = root;
        char[] chars = prefix.toCharArray();

        for(char c : chars){
            if(node.children[c-'a'] == null){
                break;
            }
            node = node.children[c-'a'];
        }
        dfs(node, prefix);
    }

    public void dfs(TriesNode node, String prefix){
        if(node.isEndOfWord){
            System.out.println(prefix);
        }
        for(char c = 'a'; c <= 'z'; c++){
            if(node.children[c-'a'] != null){
                dfs(node.children[c-'a'], prefix + c);
            }
        }
    }
}

public class TriesInJava {
    public static void main(String[] args) {
        Tries tries = new Tries();
        tries.insert("apple");
        tries.insert("app");
        tries.insert("applet");
        tries.insert("banana");
        tries.insert("bat");
        tries.insert("battle");
        tries.insert("batman");

        tries.autoComplete("app");
    }
}
