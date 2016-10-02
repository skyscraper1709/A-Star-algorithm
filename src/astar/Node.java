/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author Safat
 */
public class Node{
        Node p;
        int x,y;
        double f,g,h;
        Node(int a,int b,Node parent){
            this.x=a;
            this.y=b;
            this.p=parent;
            
        }
        Node(int a,int b){
            this.x=a;
            this.y=b;
            
        }
        public String toString(){
            return "x="+this.x+" y="+this.y+"Cost="+f;
        }
    }
