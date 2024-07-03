/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprueba;

import java.security.Security;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author jhoan
 */
public class Cifrado {
    
    public static void main(String[] args) {
        Set<String> keyGenerators = Security.getAlgorithms("KeyGenerator");
        System.out.println("Supported key generators: " + keyGenerators.stream().sorted().collect(Collectors.joining(",")));
    }
    
}
