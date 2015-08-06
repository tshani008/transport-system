/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportsystem;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author User
 */
public class SetOfTokens extends Vector<Token> implements Serializable{
    
    public SetOfTokens() {
        super();
    }
    
    public void addToken(Token token){
        super.add(token);
    }
}
