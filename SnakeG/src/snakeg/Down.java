/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeg;

/**
 *
 * @author Liranda Krasniqi
 */
public class Down implements snakeDirection 
{
     private static int DX = 0;
    private static int DY = 1;
    public int getDX()
    {
        return DX;
    }
    public int getDY()
    {
        return DY;
    }
}

