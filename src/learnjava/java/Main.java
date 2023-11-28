package learnjava.java;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        DrawArea draw = new DrawArea();
        HistoryArea history = new HistoryArea();
        int row = 0, column = 0;
        int currentGeneration=1, maxGeneration=20;
        System.out.println("Введите исходное поле.\n" +
                "\"*\" - живая клетка, \".\" - мертвая клетка.\n" +
                "В конце каждой строки вводите символ \"/\". \n" +
                "В конце поля с новой строки введите слово \"end\".");
        StringBuilder strinput= new StringBuilder();
        String newstr=scan.nextLine();
        strinput.append(newstr);
        while (!newstr.isEmpty())
        {
            newstr=scan.nextLine().toLowerCase();
            if(newstr.equals("end"))
            {
                break;
            }
            strinput.append(newstr);
        }
        System.out.println("_________");

        String[] stringArea= strinput.toString().split("/");
        column=stringArea[0].length();
        row=stringArea.length;
        if((row<2) || (column<2))
        {
            System.out.println("Установлена слишком маленькая площадь.");
            main(args);
            System.exit(0);
        }
        char[][] area = new char[row][column];
        area = draw.newArea(area, row, column);
        for (int r=0; r<stringArea.length; r++)
        {
            int dl=stringArea[r].length();
            for(int i=0; i<dl; i++)
            {
                if(dl>column)
                {
                    break;
                }
                if(stringArea[r].charAt(i)=='*') {
                    area[r][i] = '*';
                }
                else {
                    area[r][i]='.';
                }
            }
        }
        boolean flaggeneration=false;
        do
        {
            System.out.println("Установка максимального количества поколений: ");
            if(scan.hasNextInt())
            {
                maxGeneration=scan.nextInt();
                if(maxGeneration<1)
                {
                    System.out.println("Слишком мало поколений, установлено: 1");
                    maxGeneration=1;
                }
                flaggeneration=false;
            }
            else
            {
                System.out.println("Ввод неверных данных");
                scan.nextLine();
                flaggeneration=true;
            }
        }while(flaggeneration);
        System.out.println("Размер поля: "+row+"x"+column+"; генерируемых поколений: "+maxGeneration);
        System.out.println("Исходное сострояние:");
        draw.drawAreaNow(area);
        System.out.println("Живых клеток: "+draw.countAlive(area));

        boolean flag=false;
        int addGeneration=0;
        do {
            boolean continueBreak=true;
            for (int i = currentGeneration; i < maxGeneration+addGeneration; i++)
            {
                try
                {
                    Thread.sleep(250);
                }
                catch(InterruptedException ignored)   {   }
                System.out.println("\n\n\n\n\nТекущее поколение: "+currentGeneration);
                area = draw.updateArea(area);
                currentGeneration++;
                draw.drawAreaNow(area);
                int cAlive=draw.countAlive(area);
                System.out.println("Живых клеток: "+cAlive);
                if ( cAlive == 0)
                {
                    System.out.println("Игра завершена: не осталось живых клеток");
                    continueBreak=false;
                    break;
                }
                if(history.isNotDublicate(area))
                {
                    System.out.println("Игра завершена: зацикленное состояние");
                    continueBreak=false;
                    break;
                }
                history.saveArea(area);
            }



            String addStr="";
            if(continueBreak)
            {
                addStr="если желаете продолжить текущее поле, то введите \"continue\",\n";
            }

            System.out.println("\n\nЕсли желаете начать заново, то введите \"new\", \n" +
                    addStr+
                    "если желаете закончить, то введите \"end\".");

            flag=false;
            if(scan.hasNext())
            {
                String endString=scan.nextLine().toLowerCase();
                if(endString.isEmpty())
                {
                    endString=scan.nextLine().toLowerCase();
                }
                if(endString.equals("new"))
                {
                    main(args);
                    System.exit(0);
                } else if (endString.equals("continue") && continueBreak)
                {
                    boolean flaggeneration2=false;
                    do
                    {
                        System.out.println("Установка дополнительного количества поколений: ");
                        if(scan.hasNextInt())
                        {
                            addGeneration=scan.nextInt();
                            if(addGeneration<1)
                            {
                                System.out.println("Слишком мало поколений, установлено: 1");
                                addGeneration=1;
                            }
                            flaggeneration2=false;
                        }
                        else
                        {
                            System.out.println("Ввод неверных данных");
                            scan.nextLine();
                            flaggeneration2=true;
                        }
                    }while(flaggeneration2);
                    flag=true;
                }else
                {
                    System.out.println("Завершено");
                    System.exit(0);
                }
            }
            else
            {
                System.out.println("Завершено");
                System.exit(0);
            }
        }while (flag);
        System.out.println("Завершено");
        System.exit(0);
    }
}