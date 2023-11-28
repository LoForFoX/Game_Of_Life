package learnjava.java;
public class HistoryArea
{
    private String lastArea= new String();
    private String preLastArea = new String();

    public void saveArea(char[][] area)
    {
        int row = area.length;
        int column = area[0].length;

        preLastArea=lastArea;

        lastArea=convertToString(area);
    }
    public boolean isNotDublicate(char[][] area)
    {
        String str=convertToString(area);
        return(str.equals(preLastArea) || str.equals(lastArea));
    }

    public String convertToString(char[][] area)
    {
        int row = area.length;
        int column = area[0].length;
        String ss="";
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<column; j++)
            {
                ss+=area[i][j];
            }
        }
        return ss;
    }
}
