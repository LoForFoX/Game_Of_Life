public class DrawArea
{
    public void drawAreaNow(char[][] area)
    {
        //добавить метод по очистке консоли
        int row = area.length;
        int column = area[0].length;
        String ss="";
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<column; j++)
            {
                ss+=area[i][j]+" ";
            }
            if(i!=(row-1))
            {
                ss+="\n";
            }
        }
        //System.out.println("Состояние поля:\n"+ss);
        System.out.println(ss);
    }

    public char[][] newArea(char[][] area, int row, int column)
    {
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<column; j++)
            {
                area[i][j]='.';
            }
        }
        return area;
    }

    public int countAlive(char[][] area)
    {
        int counter=0;
        int row = area.length;
        int column = area[0].length;
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<column; j++)
            {
                if(area[i][j]=='*')
                {
                    counter++;
                }
            }
        }
        return counter;
    }

    public char[][] updateArea(char[][] area)
    {
        int row = area.length;
        int column = area[0].length;

        char[][] newArea = new char[row][column];
        for (int i = 0; i < area.length; i++)
        {
            System.arraycopy(area[i], 0, newArea[i], 0, area[i].length);
        }

        for(int i=0; i<row; i++)
        {
            for(int j=0; j<column; j++)
            {
                int countNeighbors=0;
                for(int ic=i-1; ic<=i+1; ic++)
                {
                    if((ic<0) || (ic==row))
                    {
                        continue;
                    }
                    for(int jc=j-1; jc<=j+1; jc++)
                    {
                        if((jc<0) || (jc==column))
                        {
                            continue;
                        }
                        if(area[ic][jc]=='*')
                        {
                            countNeighbors++;
                        }
                    }
                }
                if(area[i][j]=='*')
                {
                    countNeighbors--;
                }

                //если клетка жива и 3или2 соседа, то клетка живет, иначе умирает
                if((area[i][j]=='*') && ((countNeighbors==3) || (countNeighbors==2)))
                {
                    newArea[i][j]='*';
                }
                else
                {
                    newArea[i][j]='.';
                }

                //если клетка мертва и 3 соседа, то она оживает
                if((area[i][j]=='.') && (countNeighbors==3))
                {
                    newArea[i][j]='*';
                }
            }
        }
        return newArea;
    }
}
