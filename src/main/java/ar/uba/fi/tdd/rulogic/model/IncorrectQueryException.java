/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

/**
 *
 * @author val
 */
public class IncorrectQueryException extends RuntimeException {

    public IncorrectQueryException(String string) {
        super(string);
    }
    
}
