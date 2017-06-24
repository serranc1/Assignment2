package com.example.christian.candycrushassignment_2;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Rect;
        import android.view.MotionEvent;
        import android.view.SurfaceHolder;
        import android.view.SurfaceView;
        import java.util.ArrayList;

public class BoardView extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap icons[];
    private ArrayList<Integer> indices = new ArrayList<Integer>();
    private int prevX;
    private int prevY;
    private int startRowNumber = 9;
    private int startColumnNumber = 9;
    private int startRNumber = 0;
    private int startCNumber = 0;
    private int goal;

    //Board that is seen
    private int[][] pieces = new int[startRowNumber][startColumnNumber];
    //temp boards used for delete and swappable moves
    private int[][] deleting = new int[startRowNumber][startColumnNumber];
    private int[][] swappable = new int[startRowNumber][startColumnNumber];

    BoardView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        setWillNotDraw(false);
        System.out.println("Constructor");

        icons = new Bitmap[6];

        prevX = 0;
        prevY = 0;

        goal = 1000;

        Board board = new Board(9, 9);
        Board.generate0Board(deleting);
        Board.generate0Board(swappable);
        Board.generateRandBoard(pieces);
        Board.columnMatches(pieces, deleting);
        Board.rowMatches(pieces, deleting);

        //Check that initial board has no starting score.
        while (true) {
            int redocount = 0;
            loop4:
            for (int i = 0; i < Board.rows; i++) {
                for (int j = 0; j < Board.columns; j++) {
                    if (deleting[i][j] != 0) {
                        Board.generate0Board(deleting);
                        Board.generateRandBoard(pieces);
                        Board.columnMatches(pieces, deleting);
                        Board.rowMatches(pieces, deleting);
                        redocount = 1;
                        break loop4;
                    }
                }
            }
            if (redocount == 0) {
                System.out.println("\nSuccessful random generation of board!\n");
                break;
            }
        }

        for (int i = 0; i < Board.rows; i++) {
            for (int j = 0; j < Board.columns; j++) {
                int tmpVal = pieces[i][j];
                indices.add(tmpVal);
                System.out.println(tmpVal);
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        System.out.println("onDraw()");
        canvas.drawColor(Color.WHITE);


        Rect rect = new Rect();
        int width = (getWidth());
        int height = (getHeight()) - 225;
        int rowHeight = height / 9;
        int columnWidth = width / 9;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                rect.set(j * columnWidth, i * rowHeight, (j + 1) * columnWidth, (i + 1) * rowHeight);
                Paint q = new Paint(Color.BLACK);
                if(indices.get(i * 9 + j) == 1) {
                    q = new Paint(Color.RED);
                }
                if(indices.get(i * 9 + j) == 2) {
                    q = new Paint(Color.RED);
                }
                if(indices.get(i * 9 + j) == 3) {
                    q = new Paint(Color.BLUE);
                }
                if(indices.get(i * 9 + j) == 4) {
                    q = new Paint(Color.YELLOW);
                }
                if(indices.get(i * 9 + j) == 5) {
                    q = new Paint(Color.CYAN);
                }
                if(indices.get(i * 9 + j) == 6) {
                    q = new Paint(Color.MAGENTA);
                }
                canvas.drawBitmap(icons[indices.get(i * 9 + j) - 1], null, rect, q);
            }
        }
        Paint p = new Paint(Color.BLACK);
        p.setTextSize(45);
        String score = "Score is: "+Board.returnScore();
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(score, 25, height + 75, p);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("SurfaceCreated()");
        setWillNotDraw(false);

        icons[0] = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_1);
        icons[1] = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_2);
        icons[2] = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_3);
        icons[3] = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_4);
        icons[4] = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_5);
        icons[5] = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_6);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("touch event");
        int currentX;
        int currentY;
        int endRowNumber;
        int endColumnNumber;


        int width = (getWidth());
        int height = (getHeight()) - 225;
        int rowHeight = height / 9;
        int columnWidth = width / 9;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            prevX = (int) event.getX();
            prevY = (int) event.getY();
            startRNumber = prevY / rowHeight;
            startCNumber = prevX / columnWidth;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            currentX = (int) event.getX();
            currentY = (int) event.getY();
            endRowNumber = currentY / rowHeight;
            endColumnNumber = currentX / columnWidth;
            Board.checkPossibleVMoves(pieces, deleting, swappable);
            Board.checkPossibleHMoves(pieces, deleting, swappable);
            loop3:
            while (true) {
                for (int i = 0; i < startRowNumber; i++) {
                    for (int j = 0; j < startColumnNumber; j++) {
                        if (swappable[i][j] == 1) {
                            break loop3;
                        }
                    }
                }
            }
            int[] coordinates = {startRNumber, startCNumber, endRowNumber, endColumnNumber};
            if (Board.checkUserCoordinates(coordinates, pieces, swappable) == 1) {
                Board.columnMatches(pieces, deleting);
                Board.rowMatches(pieces, deleting);
                Board.deleteCandies(pieces, deleting);
                Board.fallingCandies(pieces);
                Board.replacingCandies(pieces);
            }
            Board.generate0Board(swappable);
            Board.generate0Board(deleting);

            while (true) {
                int redocount = 0;
                Board.columnMatches(pieces, deleting);
                Board.rowMatches(pieces, deleting);
                loop7:
                for (int i = 0; i < startRowNumber; i++) {
                    for (int j = 0; j < startColumnNumber; j++) {
                        if (deleting[i][j] != 0) {
                            redocount = 1;
                            break loop7;
                        }
                    }
                }
                Board.deleteCandies(pieces, deleting);
                Board.fallingCandies(pieces);
                Board.replacingCandies(pieces);
                if (redocount == 0) {
                    break;
                }
            }
            if (Board.returnScore() >= goal) {
                System.out.println("Score goal reached! Level completed!\n");
            }
            indices.clear();
            for (int i = 0; i < Board.rows; i++) {
                for (int j = 0; j < Board.columns; j++) {
                    int tmpVal = pieces[i][j];
                    indices.add(tmpVal);
                }
            }
        } else {
            System.out.println("Swiping...");
        }
        indices.clear();
        for (int i = 0; i < Board.rows; i++) {
            for (int j = 0; j < Board.columns; j++) {
                int tmpVal = pieces[i][j];
                indices.add(tmpVal);
            }
        }
        invalidate();

        return true;
    }
}