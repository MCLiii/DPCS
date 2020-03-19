package app;

import javax.lang.model.util.ElementScanner6;

public class Tree{
    private Treenode root;
    public Tree(String aName){
        root = new Treenode(aName);
    }
    public void add(String aName){add(aName,root);}
    public void add(String name, Treenode addNode){
        if(addNode!=null){
            if(name.compareTo(addNode.name)<0){
                if(addNode.left==null)
                    addNode.left=new Treenode(name);
                else 
                    add(name,addNode.left);
            }
            else if(name.compareTo(addNode.name)>0){
                if(addNode.right==null)
                    addNode.right=new Treenode(name);
                else
                    add(name,addNode.right);
            }
        }
    }
    public String search(String target){return search(root,target);}
    public String search(Treenode a,String target){
        if(a!=null){
            if(target.compareTo(a.name)<0)
                return("L \n"+search(a.left, target));
            else if((target.compareTo(a.name)>0))
                return("R \n"+search(a.right, target));
            else if(target.compareTo(a.name)==0)
                return "Found";
        }return "not found";
    }
}