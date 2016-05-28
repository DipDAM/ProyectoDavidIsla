/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepcion;

/**
 *
 * @author David
 */
public class MiError extends Exception{
    public MiError(String mensaje) {
        super(mensaje);
    }
}
