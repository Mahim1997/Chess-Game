/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import javafx.scene.input.MouseEvent;
//import static network.NormalFunctions.arrayCheckerX;

/**
 *
 * @author esfs
 */
public class NormalFunctions {

    public static int[][] arrayCheckerX = {{48, 103}, {103, 156}, {156, 212}, {212, 267}, {267, 323}, {323, 374}, {374, 432}, {432, 492}};
    public static int[][] arrayCheckerY = {{-98, -46}, {-46, 14}, {14, 70}, {70, 120}, {120, 180}, {180, 234}, {234, 296}, {296, 350}};

    public static double convertToMultiple(double x) {
        int i = 50, index = 0, prevIndex = 0;
        while (true) {
            if (i > x) {
                if (Math.abs(x - i) > Math.abs(prevIndex - x)) {
                    index = i;
                } else {
                    index = prevIndex;
                }
                break;
            } else {
                prevIndex = i;
                i += 50;

            }
        }
        return index;
    }

    public static int getCordY(int posY) {
        posY += 95;
        double x = ((convertToMultiple(posY) - 50)) / 50;
        if (x >= 7) {
            x = 7; //the bottom ones become 7/8 ...
        }
        return (int) (x);
    }

    public static int getCordX(int posX) {
        posX -= 55;
        double x = ((convertToMultiple(posX) - 50)) / 50;
        if (x >= 7) {
            x = 7;
        }
        return (int) (x);
    }

    public static boolean isInside(double e, int i, int j) {
        if (e > i && e <= j) {
            return true;
        } else {
            return false;
        }
    }

    public static int positionX(double e) {
        int ans = -10000;
//        double x = e.getX();
//        double y = e.getY();
        for (int i = 0; i < NormalFunctions.arrayCheckerX.length; i++) {
            if (isInside(e, NormalFunctions.arrayCheckerX[i][0], NormalFunctions.arrayCheckerX[i][1])) {
                ans = i;
                break;
            }
        }

        return ans;
    }
//    static int[][] arrayCheckerY = {{-90, -43}, {-42, 14}, {15, 70}, {71, 120}, {121, 180}, {181, 234}, {235, 293}, {294, 345}};

    public static int positionY(double e) {
        int ans = -10000;
//        double x = e.getX();
//        double y = e.getY();
        for (int i = 0; i < NormalFunctions.arrayCheckerY.length; i++) {
            if (isInside(e, NormalFunctions.arrayCheckerY[i][0], NormalFunctions.arrayCheckerY[i][1])) {
                ans = i;
                break;
            }
        }

        return ans;
    }
//    static int[][] arrayCheckerX = {{51, 103}, {104, 156}, {157, 212}, {213, 267}, {268, 323}, {324, 374}, {375, 432}, {433, 485}};

    public static int positionX(MouseEvent e) {
        int ans = -10000;
        double x = e.getX() - 55;
        double y = e.getY();
        for (int i = 0; i < NormalFunctions.arrayCheckerX.length; i++) {
            if (isInside(e.getX(), NormalFunctions.arrayCheckerX[i][0], NormalFunctions.arrayCheckerX[i][1])) {
                ans = i;
                break;
            }
        }

        return ans;
    }

    public static int positionY(MouseEvent e) {
        int ans = -10000;
        double x = e.getX();
        double y = e.getY() + 85;
        for (int i = 0; i < NormalFunctions.arrayCheckerY.length; i++) {
            if (isInside(e.getY(), NormalFunctions.arrayCheckerY[i][0], NormalFunctions.arrayCheckerY[i][1])) {
                ans = i;
                break;
            }
        }

        return ans;
    }

}
