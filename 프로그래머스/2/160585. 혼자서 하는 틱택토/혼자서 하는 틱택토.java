class Solution {
    // o 개수는 x 개수 이상이어야 한다.
    // o또는 x중에 1개만 일자여야 한다.
    
    public static String[] board;
    public static int xWin = 0;
    public static int oWin = 0;
    public static int xCnt = 0;
    public static int oCnt = 0;
    public int solution(String[] board) {
        this.board = board;
        // 조건 1 체크
        checkCount();
        if( oCnt != xCnt && oCnt != xCnt + 1) return 0;
        
        xWin = isWinCount("X");
        oWin = isWinCount("O");
        //System.out.println("조건 1 성공");
        
        // 조건 2. 둘 중에 xWin 
        if( xWin > 0 && oWin > 0 ) return 0;
        
        if(xWin == 1) {
            if(oCnt != xCnt) return 0;
        }
        if(oWin == 1) {
            if(oCnt != xCnt + 1) return 0;
        }
        
        return 1;   
    }
    
    public void checkCount() {
        for(int y = 0 ; y < 3; y++) {
            for(int x = 0; x < 3; x++) {
                if(board[y].charAt(x) == 'X') xCnt++;
                if(board[y].charAt(x) == 'O') oCnt++;
            }
        }
    }
    
    public int isWinCount(String str) {
        int cnt = 0;
        // 가로축 체크
        for(int y = 0 ; y < 3; y++) {
            for(int x = 0; x < 3; x++) {
                if(board[y].charAt(x) != str.charAt(0)) break;
                if(board[y].charAt(x) == str.charAt(0) && x == 2) {
                    cnt++;
                }
            }
        }
        
        // 세로축 체크
        for(int x = 0 ; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                if(board[y].charAt(x) != str.charAt(0)) break;
                if(board[y].charAt(x) == str.charAt(0) && y == 2) {
                    cnt++;
                }
            }
        }
        
        // 대각선 체크
        if( str.charAt(0) == board[0].charAt(0) &&
            board[0].charAt(0) == board[1].charAt(1) &&
            board[1].charAt(1) == board[2].charAt(2)
          ) cnt++;
        
        if( str.charAt(0) == board[0].charAt(2) &&
            board[0].charAt(2) == board[1].charAt(1) &&
            board[1].charAt(1) == board[2].charAt(0)
          ) cnt++;
        return cnt;
    }
}