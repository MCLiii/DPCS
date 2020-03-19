package app;

public class Treenode {
    public String name;
    public Treenode left;
    public Treenode right;
    public Treenode(String aname){
        this.name=aname;
        this.left=null;
        this.right=null;
    }
}