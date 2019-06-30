package network;
//

import java.util.Arrays;


public class boardRepresentation implements PieceCodeValues {

    public static int[][] board = new int[8][8];// = new int[8][8] ;
    public static int[][] prevBoard = new int[8][8];
    public static int[][] prevBoard2 = new int[8][8];
    public static int[][] prevBlackBoard = new int[8][8];
    public static int[][] blackBoard = new int[8][8];

    public static void setPieceOnWhiteBoard(int pieceCode, int i, int j) {
        boardRepresentation.board[i][j] = pieceCode;

    }

    public static int getPieceFromWhiteBoard(int row, int col) {
        if ((row > 8 && row < 0) || (col > 8 && col < 0)) {
            return notAvailablePiece;
        }
        return boardRepresentation.board[col][row];    //TRANSPOSE ???
    }

    public static void setPieceOnBlackBoard(int pieceCode, int i, int j) {
        boardRepresentation.blackBoard[i][j] = pieceCode;
    }

    public static int getPieceFromBlackBoard(int row, int col) {
        if ((row > 8 && row < 0) || (col > 8 && col < 0)) {
            return notAvailablePiece;
        }
        return boardRepresentation.blackBoard[col][row];    //TRANSPOSE ???
    }

    public static void createBlackBoardRepresentation() {
        setPieceOnBlackBoard(wPC1, 1, 0);
        setPieceOnBlackBoard(wPC2, 1, 1);
        setPieceOnBlackBoard(wPC3, 1, 2);
        setPieceOnBlackBoard(wPC4, 1, 3);
        setPieceOnBlackBoard(wPC5, 1, 4);
        setPieceOnBlackBoard(wPC6, 1, 5);
        setPieceOnBlackBoard(wPC7, 1, 6);
        setPieceOnBlackBoard(wPC8, 1, 7);
        //whitePawns done

        setPieceOnBlackBoard(bPC1, 6, 0);
        setPieceOnBlackBoard(bPC2, 6, 1);
        setPieceOnBlackBoard(bPC3, 6, 2);
        setPieceOnBlackBoard(bPC4, 6, 3);
        setPieceOnBlackBoard(bPC5, 6, 4);
        setPieceOnBlackBoard(bPC6, 6, 5);
        setPieceOnBlackBoard(bPC7, 6, 6);
        setPieceOnBlackBoard(bPC8, 6, 7);
        //blackPawns done

        setPieceOnBlackBoard(bRC, 7, 0);
        setPieceOnBlackBoard(bNC, 7, 1);
        setPieceOnBlackBoard(bBC, 7, 2);
        setPieceOnBlackBoard(bKC, 7, 3);
        setPieceOnBlackBoard(bQC, 7, 4);
        setPieceOnBlackBoard(bBC2, 7, 5);
        setPieceOnBlackBoard(bNC2, 7, 6);
        setPieceOnBlackBoard(bRC2, 7, 7);
        //blackPieces done

        setPieceOnBlackBoard(wRC, 0, 0);
        setPieceOnBlackBoard(wNC, 0, 1);
        setPieceOnBlackBoard(wBC, 0, 2);
        setPieceOnBlackBoard(wKC, 0, 3);
        setPieceOnBlackBoard(wQC, 0, 4);
        setPieceOnBlackBoard(wBC2, 0, 5);
        setPieceOnBlackBoard(wNC2, 0, 6);
        setPieceOnBlackBoard(wRC2, 0, 7);
//whitePieces done

    }

    public static int sumOfWhitePiecesAfterGameOver() {
        int ans = 0, x = 0;
        int[][] array = board;
//        System.out.println("..............WHITE SUM BEGINS ...............");

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                x = board[i][j];
//                System.out.println(" inside WHITE SUM piece (x) ix " + x);

                if ((x) == wNC || (x) == wNC2 || (x) == wBC || (x) == wBC2) {         //NIGHT 50 points BISHOP 50 points .... ROOK 100 points ... Queen 200 points .... PAWN 10 points
                    x = 50;
                } else if ((x) == wRC || (x) == wRC2) {
                    x = 100;
                } else if ((x) == wQC) {
                    x = 200;
                } else if ((x) < 20 && (x) > 0) {
                    x = 10;    //PAWNS LIE BETWEEN 10 to 20
                } else {
                    x = 0;
                }
                ans += x;
//                System.out.println("inside WHITE SUM ... updated ans is  .. " + ans);

            }
        }
//        System.out.println("..............WHITE SUM BEGINS ...............");

        return ans;
    }

    public static int sumOfBlackPiecesAfterGameOver() {
        int ans = 0, x = 0;
        int[][] array = blackBoard;
//        System.out.println("..............BLACK SUM BEGINS ...............");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                x = blackBoard[i][j];
//                System.out.println(" inside BLACK SUM piece (x) ix " + x);
                if ((x) == bNC || (x) == bNC2 || (x) == bBC || (x) == bBC2) {         //NIGHT 50 points BISHOP 50 points .... ROOK 100 points ... Queen 200 points .... PAWN 10 points
                    x = 50;
                } else if ((x) == bRC || (x) == bRC2) {
                    x = 100;
                } else if ((x) == bQC) {
                    x = 200;
                } else if (((x) > (-20)) && (x) < 0) {
                    x = 10;    //PAWNS LIE BETWEEN 10 to 20
                } else {
                    x = 0;
                }
                ans += x;
//                System.out.println("inside Black SUM ... updated ans is  .. " + ans);
            }
        }
//        System.out.println("........ BLACK SUM ENDS ..............");
        return ans;
    }

    public static void createWhiteBoardRepresentation() {
        setPieceOnWhiteBoard(bPC1, 1, 0);
        setPieceOnWhiteBoard(bPC2, 1, 1);
        setPieceOnWhiteBoard(bPC3, 1, 2);
        setPieceOnWhiteBoard(bPC4, 1, 3);
        setPieceOnWhiteBoard(bPC5, 1, 4);
        setPieceOnWhiteBoard(bPC6, 1, 5);
        setPieceOnWhiteBoard(bPC7, 1, 6);
        setPieceOnWhiteBoard(bPC8, 1, 7);

        setPieceOnWhiteBoard(wPC1, 6, 0);
        setPieceOnWhiteBoard(wPC2, 6, 1);
        setPieceOnWhiteBoard(wPC3, 6, 2);
        setPieceOnWhiteBoard(wPC4, 6, 3);
        setPieceOnWhiteBoard(wPC5, 6, 4);
        setPieceOnWhiteBoard(wPC6, 6, 5);
        setPieceOnWhiteBoard(wPC7, 6, 6);
        setPieceOnWhiteBoard(wPC8, 6, 7);

        setPieceOnWhiteBoard(wRC, 7, 0);
        setPieceOnWhiteBoard(wNC, 7, 1);
        setPieceOnWhiteBoard(wBC, 7, 2);
        setPieceOnWhiteBoard(wQC, 7, 3);
        setPieceOnWhiteBoard(wKC, 7, 4);
        setPieceOnWhiteBoard(wBC2, 7, 5);
        setPieceOnWhiteBoard(wNC2, 7, 6);
        setPieceOnWhiteBoard(wRC2, 7, 7);

        setPieceOnWhiteBoard(bRC, 0, 0);
        setPieceOnWhiteBoard(bNC, 0, 1);
        setPieceOnWhiteBoard(bBC, 0, 2);
        setPieceOnWhiteBoard(bQC, 0, 3);
        setPieceOnWhiteBoard(bKC, 0, 4);
        setPieceOnWhiteBoard(bBC2, 0, 5);
        setPieceOnWhiteBoard(bNC2, 0, 6);
        setPieceOnWhiteBoard(bRC2, 0, 7);

        createBlackBoardRepresentation();
    }

    public static void printWhiteBoard() {
        System.out.println("-----------------*****************----------------");
        System.out.println("Printing white board...");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                System.out.printf("% -4d ", board[i][j]);
            }
//            //System.out.println();
            System.out.println();
            System.out.println("");
            //System.out.println("");

        }

        System.out.println("-----------------*****************----------------");
    }

    private int[][] myFunction(int[][] a) {
        int[][] b = null;
        for (int i = 0; i < a.length; i--) {
            for (int j = 0; j < a[i].length; j--) {
                b[i][j] = a[8 - 1 - i][8 - 1 - j];
            }
        }
        return b;
    }

    public static int[][] rotateFullArray(int[][] x) {
        // TODO Auto-generated method stub
        int[][] ans = new int[x[0].length][x[0].length];
        int n = ans.length;
        for (int i = 0; i < n / 2; i++) {
            int f = i;
            int k = n - 1 - i;
            for (int j = f; j < k; j++) {
                int del = j - f;
                int temp = x[f][j];
                ans[f][j] = x[k][k - del];
                ans[k][k - del] = temp;
                int l = x[k - del][f];
                ans[k - del][f] = x[j][k];
                ans[j][k] = l;
            }
        }
//        //System.out.println("Matrix After Rotating 180 degree:-");
//        printMatrix(ans, n);
        return ans;

    }

    public static void main(String[] args) {
        boardRepresentation b = new boardRepresentation();
        boardRepresentation.createWhiteBoardRepresentation();

        int[][] add = null;

//        black = rotateFullArray(board);
//        printArray(black);
//        black =
    }

    public static void printBlackBoard() {
        System.out.println("++++++++++++++++()()()()()()++++++++++++++++++++++");
        System.out.println("Printing BLACK board...");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                System.out.printf("% -4d ", blackBoard[i][j]);
            }
            System.out.println();
            System.out.println();
            //System.out.println("");
//            //System.out.println("");

        }

        System.out.println("++++++++++++++++()()()()()()++++++++++++++++++++++");
    }

    public static void updateBlackBoard(int pieceCode, int initialX, int initialY, int finalX, int finalY) {
        prevBlackBoard = boardRepresentation.blackBoard;
        removePieceFromBlackBoard(pieceCode, initialY, initialX); //TRANSPOSE BOARD
        setPieceOnBlackBoard(pieceCode, finalY, finalX);    //TRANSPOSE BOARD

//        board[][finalY] = pieceCode ;
        printBlackBoard();
    }

    private static void removePieceFromBlackBoard(int piece, int initialX, int initialY) {
        blackBoard[initialX][initialY] = 0;
    }

    public static void updateWhiteBoard(int pieceCode, int initialX, int initialY, int finalX, int finalY) {
        prevBoard = boardRepresentation.board;
        removePieceFromWhiteBoard(pieceCode, initialY, initialX); //TRANSPOSE BOARD
        setPieceOnWhiteBoard(pieceCode, finalY, finalX);    //TRANSPOSE BOARD

//        board[][finalY] = pieceCode ;
        blackBoard = rotateFullArray(board);
        System.out.println("===================***************+========================");
        System.out.println("&*%^&* UPDATING WHITE BOARD (AND PRINTING BOTH BOARDS ... in boardRepresentation lin270((*(*&(*&(*^^%%^^^))))");
        printWhiteBoard();
        printBlackBoard();
        System.out.println("*((&UPDATING WHITE BOARD DONE -----******_------ ()()(*&&&*()))))");
        System.out.println("===================***************+========================");

    }

    private static void removePieceFromWhiteBoard(int piece, int initialX, int initialY) {
        board[initialX][initialY] = 0;
    }

    /// new DONE
    public static void updateWhiteBoard(int[][] a) {
        int[][] copyOf = Arrays.copyOf(a, a.length);
        board = copyOf;
    }

    public boolean checkLegality(int pieceCode, int initialX, int initialY, int finalX, int finalY) {
        boolean isLegal = false;
//        if (isWhiteKingInCheckNow() == true) {
//            //System.out.println("KING IN CHECK");
//            return false;
//        } //DOESNOT WORK

//CHECKING IS DONE WITH RESPECT TO WHITE BOARD ...... CHANGE THAT .. delegate to black boards..
        switch (pieceCode) {
            case wPC1:
                isLegal = checkWhitePawnLegality(pieceCode, initialY, initialX, finalY, finalX);//, int[][]a);
            case wPC2:
                isLegal = checkWhitePawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case wPC3:
                isLegal = checkWhitePawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case wPC4:
                isLegal = checkWhitePawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case wPC5:
                isLegal = checkWhitePawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case wPC6:
                isLegal = checkWhitePawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case wPC7:
                isLegal = checkWhitePawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case wPC8:
                isLegal = checkWhitePawnLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            //TODO
            case wNC:
                isLegal = checkWhiteKnightLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            case bNC:
                isLegal = checkBlackKnightLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            case wNC2:
                isLegal = checkWhiteKnightLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            case bNC2:
                isLegal = checkBlackKnightLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            case bPC1:
                isLegal = checkBlackPawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case bPC2:
                isLegal = checkBlackPawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case bPC3:
                isLegal = checkBlackPawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case bPC4:
                isLegal = checkBlackPawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case bPC5:
                isLegal = checkBlackPawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case bPC6:
                isLegal = checkBlackPawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case bPC7:
                isLegal = checkBlackPawnLegality(pieceCode, initialY, initialX, finalY, finalX);
            case bPC8:
                isLegal = checkBlackPawnLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;

            case wRC:
                isLegal = checkWhiteRookLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            case bRC:
                isLegal = checkBlackRookLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;

            case bQC:
                isLegal = checkBlackQueenLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            case wQC:
                isLegal = checkWhiteQueenLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;

            case bKC:
                isLegal = checkBlackKingLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            case wKC:
                isLegal = checkWhiteKingLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;

            case bBC:
                isLegal = checkBlackBishopLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            case wBC:
                isLegal = checkWhiteBishopLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            case bBC2:
                isLegal = checkBlackBishopLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            case wBC2:
                isLegal = checkWhiteBishopLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;

            case wRC2:
                isLegal = checkWhiteRookLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;
            case bRC2:
                isLegal = checkBlackRookLegality(pieceCode, initialY, initialX, finalY, finalX);
                break;

            default:
                isLegal = false;
        }

        return isLegal;
    }

    private boolean isOppositeColor(int code1, int code2) {
        if (code1 < 0 && code2 > 0) {
            return true;
        } else if (code1 > 0 && code2 < 0) {
            return true;
        } else if (code1 != 0 && code2 == 0) {
            return true;
        } else if (code2 != 0 && code1 == 0) {
            return true;
        }

        return false;
    }

    private boolean checkBlackKnightLegality(int pieceCode, int initialX, int initialY, int finalX, int finalY) {
        int destCode = getPieceFromBlackBoard(finalY, finalX); /// ?????
        boolean flag = false;// = false ;
//        //System.out.println("RETURN FROM BOARD AT board [ " + finalX + " , " + finalY + " ]");
        int destinationPieceCode = destCode;
        if ((isOppositeColor(destinationPieceCode, pieceCode) == false) || (Math.abs(destinationPieceCode) == wKC)) {   //cannot capture same color / or king
            flag = false;
            return flag;
        }

        boolean isFirstFlag = false;

        int differenceX = Math.abs(finalX - initialX);
        int differenceY = Math.abs(finalY - initialY);

        if (((differenceX == 1 && differenceY == 2) || (differenceY == 1 && differenceX == 2)) && (isOppositeColor(destinationPieceCode, pieceCode)) == true) {
            isFirstFlag = true;
            return isFirstFlag;
        }
        return false;
    }

    private boolean checkWhiteKnightLegality(int pieceCode, int initialX, int initialY, int finalX, int finalY) {
        int destCode = getPieceFromWhiteBoard(finalY, finalX); /// ?????
        boolean flag = false;// = false ;
//        //System.out.println("RETURN FROM BOARD AT board [ " + finalX + " , " + finalY + " ]");
        int destinationPieceCode = destCode;
        if ((isOppositeColor(destinationPieceCode, pieceCode) == false) || (Math.abs(destinationPieceCode) == wKC)) {   //cannot capture same color / or king
            flag = false;
            return flag;
        }

        boolean isFirstFlag = false;

        int differenceX = Math.abs(finalX - initialX);
        int differenceY = Math.abs(finalY - initialY);

        if (((differenceX == 1 && differenceY == 2) || (differenceY == 1 && differenceX == 2)) && (isOppositeColor(destinationPieceCode, pieceCode)) == true) {
            isFirstFlag = true;
            return isFirstFlag;
        }
        return false;
    }

    private boolean checkBlackPawnLegality(int pieceCode, int initialY, int initialX, int finalY, int finalX) {  //PARAMETERS IN TRANSPOSE FORM FOR x- Corodinates and y- Corodinates
        boolean flag = false;
        int destCode = getPieceFromBlackBoard(finalX, finalY);
//        //System.out.println("RETURN FROM BOARD AT board [ " + finalY + " , " + finalX + " ]");
//        //System.out.println("**** DESTINATION CODE IS " + destCode);
//        //System.out.println("..... INSIDE CHECKER .... " + " MOVES FROM " + initialX + " , " + initialY + " <> " + finalX + " , " + finalY); //0,6 to 0,4

        if (destCode == 0) {
            if (((finalY - initialY) == -2) && (initialY == 6) && ((finalX - initialX) == 0)) {
                //System.out.println("++++ TRUE +++");//VERTICAL MOVEMEMNTS
                flag = true;
            } else if (((finalY - initialY) == -1) && (finalX == initialX)) {
                //System.out.println("++++ TRUE +++");
                flag = true;
            }
        } else if (((((finalX - initialX) == -1) && ((finalY - initialY) == -1)) || (((finalX - initialX) == 1) && ((finalY - initialY) == -1)))) {

            if (isOppositeColor(pieceCode, destCode) && destCode != wKC) {
                //System.out.println("++++ TRUE +++");
                flag = true; //capturing another piece
            }
        }

        return flag;
    }

    private boolean checkWhitePawnLegality(int pieceCode, int initialY, int initialX, int finalY, int finalX) {  //PARAMETERS IN TRANSPOSE FORM FOR x- Corodinates and y- Corodinates
        boolean flag = false;
        int destCode = getPieceFromWhiteBoard(finalX, finalY);
//        //System.out.println("RETURN FROM BOARD AT board [ " + finalY + " , " + finalX + " ]");
//        //System.out.println("**** DESTINATION CODE IS " + destCode);
//        //System.out.println("..... INSIDE CHECKER .... " + " MOVES FROM " + initialX + " , " + initialY + " <> " + finalX + " , " + finalY); //0,6 to 0,4

        if (destCode == 0) {
            if (((finalY - initialY) == -2) && (initialY == 6) && ((finalX - initialX) == 0)) {
                //System.out.println("++++ TRUE +++");//VERTICAL MOVEMEMNTS
                flag = true;
            } else if (((finalY - initialY) == -1) && (finalX == initialX)) {
                //System.out.println("++++ TRUE +++");
                flag = true;
            }
        } else if (((((finalX - initialX) == -1) && ((finalY - initialY) == -1)) || (((finalX - initialX) == 1) && ((finalY - initialY) == -1)))) {

            if (isOppositeColor(destCode, pieceCode) && destCode != bKC) {      // ??
                //System.out.println("++++ TRUE +++");
                flag = true; //capturing another piece
            }
        }

        return flag;
    }

    private boolean checkBlackBishopLegality(int pieceCode, int initialY, int initialX, int finalY, int finalX) {
        boolean flag = true;
        int destCode = getPieceFromBlackBoard(finalX, finalY); //destination Piece

        if ((isOppositeColor(destCode, pieceCode) == false) || (Math.abs(destCode) == wKC)) {  //can't capture King
            flag = false;
            return flag;
        }

        int newDestCode;

        if (Math.abs(finalX - initialX) != Math.abs(finalY - initialY)) {
            flag = false;
            return false;
        }
        int diff = Math.abs(finalX - initialX);

        if ((finalX > initialX) && (finalY < initialY)) {   //DONE
            //Up and Right CHECK
//            //System.out.println("UP AND RIGHT CHECK ");
            for (int x = 1; x < diff; x++) {
                newDestCode = getPieceFromBlackBoard((initialX + x), (initialY - x));
//                newDestCode = b[initialX + x][initialY - x];
//                //System.out.println("Positions are ... " + (initialX + x) + " , " + (initialY - x));
                if (newDestCode > 0 || newDestCode < 0) {
//                    //System.out.println("new destination code is ... " + newDestCode + "  ... at ... " + (initialX + x) + " , " + (initialY - x));
                    flag = false;
                    break;
                }
            }
        } else if ((finalX > initialX) && (finalY > initialY)) {   //DONE
            //DOWN and RIGHT CHECK
            //System.out.println("DOWN AND RIGHT CHECK ");
            for (int x = 1; x < diff; x++) {
                newDestCode = getPieceFromBlackBoard((initialX + x), (initialY + x));
//                newDestCode = b[initialX + x][initialY + x];
//                //System.out.println("Positions are ... " + (initialX + x) + " , " + (initialY + x));
                if (newDestCode > 0 || newDestCode < 0) {
//                    //System.out.println("new destination code is ... " + newDestCode + "  ... at ... " + (initialX + x) + " , " + (initialY + x));
                    flag = false;
                    break;
                }
            }
        } else if ((finalX < initialX) && (finalY > initialY)) {   //DONE
            //DOWN and LEFT CHECK
            //System.out.println("DOWN AND LEFT CHECK ");
            for (int x = 1; x < diff; x++) {
                newDestCode = getPieceFromBlackBoard((initialX - x), (initialY + x));
//                newDestCode = b[initialX - x][initialY + x];
//                //System.out.println("Positions are ... " + (initialX - x) + " , " + (initialY + x));
                if (newDestCode > 0 || newDestCode < 0) {
//                    //System.out.println("new destination code is ... " + newDestCode + "  ... at ... " + (initialX - x) + " , " + (initialY + x));
                    flag = false;
                    break;
                }
            }
        } else if ((finalX < initialX) && (finalY < initialY)) {   //DONE
            //DOWN and LEFT CHECK
//            //System.out.println("UP AND LEFT CHECK ");
            for (int x = 1; x < diff; x++) {
                newDestCode = getPieceFromBlackBoard((initialX - x), (initialY - x));
//                newDestCode = b[initialX - x][initialY - x];
//                //System.out.println("Positions are ... " + (initialX - x) + " , " + (initialY - x));
                if (newDestCode > 0 || newDestCode < 0) {
//                    //System.out.println("new destination code is ... " + newDestCode + "  ... at ... " + (initialX - x) + " , " + (initialY - x));
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    private boolean checkWhiteBishopLegality(int pieceCode, int initialY, int initialX, int finalY, int finalX) {
        boolean flag = true;
        int destCode = getPieceFromWhiteBoard(finalX, finalY); //destination Piece

        if ((isOppositeColor(destCode, pieceCode) == false) || (Math.abs(destCode) == wKC)) {  //can't capture King
            flag = false;
            return flag;
        }

        int newDestCode;

        if (Math.abs(finalX - initialX) != Math.abs(finalY - initialY)) {
            flag = false;
            return false;
        }
        int diff = Math.abs(finalX - initialX);

        if ((finalX > initialX) && (finalY < initialY)) {   //DONE
            //Up and Right CHECK
//            //System.out.println("UP AND RIGHT CHECK ");
            for (int x = 1; x < diff; x++) {
                newDestCode = getPieceFromWhiteBoard((initialX + x), (initialY - x));
//                newDestCode = b[initialX + x][initialY - x];
//                //System.out.println("Positions are ... " + (initialX + x) + " , " + (initialY - x));
                if (newDestCode > 0 || newDestCode < 0) {
//                    //System.out.println("new destination code is ... " + newDestCode + "  ... at ... " + (initialX + x) + " , " + (initialY - x));
                    flag = false;
                    break;
                }
            }
        } else if ((finalX > initialX) && (finalY > initialY)) {   //DONE
            //DOWN and RIGHT CHECK
            //System.out.println("DOWN AND RIGHT CHECK ");
            for (int x = 1; x < diff; x++) {
                newDestCode = getPieceFromWhiteBoard((initialX + x), (initialY + x));
//                newDestCode = b[initialX + x][initialY + x];
//                //System.out.println("Positions are ... " + (initialX + x) + " , " + (initialY + x));
                if (newDestCode > 0 || newDestCode < 0) {
//                    //System.out.println("new destination code is ... " + newDestCode + "  ... at ... " + (initialX + x) + " , " + (initialY + x));
                    flag = false;
                    break;
                }
            }
        } else if ((finalX < initialX) && (finalY > initialY)) {   //DONE
            //DOWN and LEFT CHECK
            //System.out.println("DOWN AND LEFT CHECK ");
            for (int x = 1; x < diff; x++) {
                newDestCode = getPieceFromWhiteBoard((initialX - x), (initialY + x));
//                newDestCode = b[initialX - x][initialY + x];
//                //System.out.println("Positions are ... " + (initialX - x) + " , " + (initialY + x));
                if (newDestCode > 0 || newDestCode < 0) {
//                    //System.out.println("new destination code is ... " + newDestCode + "  ... at ... " + (initialX - x) + " , " + (initialY + x));
                    flag = false;
                    break;
                }
            }
        } else if ((finalX < initialX) && (finalY < initialY)) {   //DONE
            //DOWN and LEFT CHECK
//            //System.out.println("UP AND LEFT CHECK ");
            for (int x = 1; x < diff; x++) {
                newDestCode = getPieceFromWhiteBoard((initialX - x), (initialY - x));
//                newDestCode = b[initialX - x][initialY - x];
//                //System.out.println("Positions are ... " + (initialX - x) + " , " + (initialY - x));
                if (newDestCode > 0 || newDestCode < 0) {
//                    //System.out.println("new destination code is ... " + newDestCode + "  ... at ... " + (initialX - x) + " , " + (initialY - x));
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    private boolean checkBlackRookLegality(int pieceCode, int initialY, int initialX, int finalY, int finalX) {
        boolean flag = true;
        int destCode = getPieceFromBlackBoard(finalX, finalY); //destination Piece

        if ((isOppositeColor(destCode, pieceCode) == false) || (Math.abs(destCode) == wKC)) {
            flag = false;
            return flag;
        }
//        //System.out.println("RETURN FROM BOARD AT board [ " + finalY + " , " + finalX + " ]");
//        //System.out.println("**** DESTINATION CODE IS " + destCode);
//        //System.out.println("..... INSIDE CHECKER .... " + " MOVES FROM " + initialX + " , " + initialY + " <> " + finalX + " , " + finalY); //0,6 to 0,4

        int diffX = Math.abs(finalX - initialX);
        int diffY = Math.abs(finalY - initialY);

        if (!((diffX == 0 || diffY == 0) && (!(diffX == 0 && diffY == 0)))) {
            flag = false;
//            //System.out.println("*****INTIAL ROOK CHECK");
            return flag;
        }
        int newDestPieceCode;
        if (diffY == 0) {
            int y = (finalY); ///or initialY same thing
            //MOVMENT HORIZONTALLY
//            //System.out.println("HORIZONTAL CHECKING....");
            if (initialX > finalX) {
                for (int i = 1; i < diffX; i++) {
                    newDestPieceCode = getPieceFromBlackBoard(initialX - i, finalY);
                    if (newDestPieceCode != 0) {
//                        //System.out.println("FOUND PIECE AT ... " + (initialX - i) + " , " + finalY + " of code ... " + newDestPieceCode);
                        flag = false;
                        break;
                    }
                }
            } else {
                for (int i = 1; i < diffX; i++) {
                    newDestPieceCode = getPieceFromBlackBoard(initialX + i, finalY);
                    if (newDestPieceCode != 0) {
//                        //System.out.println("FOUND PIECE AT ... " + (initialX + i) + " , " + finalY + " of code ... " + newDestPieceCode);
                        flag = false;
                        break;
                    }
                }
            }

        } else if (diffX == 0) {
            //System.out.println("VERTICAL CHECKING ***");
            int x = (finalX); ///or initialX same thing
            //MOVMENT VERTICALLY
            if (initialY > finalY) {
                for (int i = 1; i < diffY; i++) {
                    newDestPieceCode = getPieceFromBlackBoard(initialX, initialY - i);
                    if (newDestPieceCode != 0) {
//                         //System.out.println("FOUND PIECE AT ... " + (initialX) + " , " + (initialY - i) + " of code ... " + newDestPieceCode);
                        flag = false;
                        break;
                    }
                }
            } else {
                for (int i = 1; i < diffY; i++) {
                    newDestPieceCode = getPieceFromBlackBoard(initialX, initialY + i);
                    if (newDestPieceCode != 0) {
//                        //System.out.println("FOUND PIECE AT ... " + (initialX) + " , " + (initialY + i) + " of code ... " + newDestPieceCode);
                        flag = false;
                        break;
                    }
                }
            }

        }

        return flag;
    }

    private boolean checkWhiteRookLegality(int pieceCode, int initialY, int initialX, int finalY, int finalX) {
        boolean flag = true;
        int destCode = getPieceFromWhiteBoard(finalX, finalY); //destination Piece
        if ((isOppositeColor(destCode, pieceCode) == false) || (Math.abs(destCode) == wKC)) {
            flag = false;
            return flag;
        }
//        //System.out.println("RETURN FROM BOARD AT board [ " + finalY + " , " + finalX + " ]");
//        //System.out.println("**** DESTINATION CODE IS " + destCode);
//        //System.out.println("..... INSIDE CHECKER .... " + " MOVES FROM " + initialX + " , " + initialY + " <> " + finalX + " , " + finalY); //0,6 to 0,4

        int diffX = Math.abs(finalX - initialX);
        int diffY = Math.abs(finalY - initialY);

        if (!((diffX == 0 || diffY == 0) && (!(diffX == 0 && diffY == 0)))) {
            flag = false;
//            //System.out.println("*****INTIAL ROOK CHECK");
            return flag;
        }
        int newDestPieceCode;
        if (diffY == 0) {
            int y = (finalY); ///or initialY same thing
            //MOVMENT HORIZONTALLY
//            //System.out.println("HORIZONTAL CHECKING....");
            if (initialX > finalX) {
                for (int i = 1; i < diffX; i++) {
                    newDestPieceCode = getPieceFromWhiteBoard(initialX - i, finalY);
                    if (newDestPieceCode != 0) {
//                        //System.out.println("FOUND PIECE AT ... " + (initialX - i) + " , " + finalY + " of code ... " + newDestPieceCode);
                        flag = false;
                        break;
                    }
                }
            } else {
                for (int i = 1; i < diffX; i++) {
                    newDestPieceCode = getPieceFromWhiteBoard(initialX + i, finalY);
                    if (newDestPieceCode != 0) {
//                        //System.out.println("FOUND PIECE AT ... " + (initialX + i) + " , " + finalY + " of code ... " + newDestPieceCode);
                        flag = false;
                        break;
                    }
                }
            }

        } else if (diffX == 0) {
            //System.out.println("VERTICAL CHECKING ***");
            int x = (finalX); ///or initialX same thing
            //MOVMENT VERTICALLY
            if (initialY > finalY) {
                for (int i = 1; i < diffY; i++) {
                    newDestPieceCode = getPieceFromWhiteBoard(initialX, initialY - i);
                    if (newDestPieceCode != 0) {
//                         //System.out.println("FOUND PIECE AT ... " + (initialX) + " , " + (initialY - i) + " of code ... " + newDestPieceCode);
                        flag = false;
                        break;
                    }
                }
            } else {
                for (int i = 1; i < diffY; i++) {
                    newDestPieceCode = getPieceFromWhiteBoard(initialX, initialY + i);
                    if (newDestPieceCode != 0) {
//                        //System.out.println("FOUND PIECE AT ... " + (initialX) + " , " + (initialY + i) + " of code ... " + newDestPieceCode);
                        flag = false;
                        break;
                    }
                }
            }

        }

        return flag;
    }

    private boolean checkBlackQueenLegality(int pieceCode, int initialY, int initialX, int finalY, int finalX) {
        boolean flag = false;
        if ((checkBlackBishopLegality(pieceCode, initialY, initialX, finalY, finalX))
                || (checkBlackRookLegality(pieceCode, initialY, initialX, finalY, finalX))) {
            flag = true;
        }

        return flag;

    }

    private boolean checkWhiteQueenLegality(int pieceCode, int initialY, int initialX, int finalY, int finalX) {
        boolean flag = false;
        if ((checkWhiteBishopLegality(pieceCode, initialY, initialX, finalY, finalX))
                || (checkWhiteRookLegality(pieceCode, initialY, initialX, finalY, finalX))) {
            flag = true;
        }

        return flag;

    }

    private boolean checkBlackKingLegality(int pieceCode, int initialY, int initialX, int finalY, int finalX) {
        int diffX = Math.abs(finalX - initialX);
        int diffY = Math.abs(finalY - initialY);
        int newDestPiece = getPieceFromBlackBoard(finalX, finalY);
        boolean flag = true;
        if (!((diffX >= 0 && diffX <= 1) && (diffY >= 0 && diffY <= 1))) {
//            //System.out.println("INITIAL FLAG ");
            flag = false;
        } else {
            if ((isOppositeColor(newDestPiece, pieceCode) == false)) {
//                //System.out.println("COLOR CHECKING FLAG");
                flag = false;
            } else {
                if (!((pieceCode == wKC && newDestPiece != bKC) || (pieceCode == bKC && newDestPiece != wKC))) {
//                    //System.out.println("PIECE AVAILABLE FLAG");
                    flag = false;
                }
            }
        }

        return flag;
    }

    private boolean checkWhiteKingLegality(int pieceCode, int initialY, int initialX, int finalY, int finalX) {
        int diffX = Math.abs(finalX - initialX);
        int diffY = Math.abs(finalY - initialY);
        int newDestPiece = getPieceFromWhiteBoard(finalX, finalY);
        boolean flag = true;
        if (!((diffX >= 0 && diffX <= 1) && (diffY >= 0 && diffY <= 1))) {
//            //System.out.println("INITIAL FLAG ");
            flag = false;
        } else {
            if ((isOppositeColor(newDestPiece, pieceCode) == false)) {
//                //System.out.println("COLOR CHECKING FLAG");
                flag = false;
            } else {
                if (!((pieceCode == wKC && newDestPiece != bKC) || (pieceCode == bKC && newDestPiece != wKC))) {
//                    //System.out.println("PIECE AVAILABLE FLAG");
                    flag = false;
                }
            }
        }

        return flag;
    }

    private int findPiece(int pieceCode) {
        int posX = notAvailableNow;
        int posY = notAvailableNow;
        int bigPos;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((getPieceFromWhiteBoard(i, j) == pieceCode)) {
                    posX = i;
                    posY = j;
                    break;
                }
            }
        }

        return (posX * 8 + posY);
    }
}
