package View;

import java.io.Console;
import java.io.IOException;

public class MenuView implements IMenuView
{
    private int selectedIndex;
    private String[] options;

    public MenuView(String[] options)
    {
        this.selectedIndex=0;
        this.options = options;
    }

    @Override
    public void DisplayOptions()
    {
        for (int i = 0; i < options.length; i++)
        {
            String currentOption = options[i];
            String prefix;

            if (i == selectedIndex)
            {
                prefix = "-->  ";
                System.out.print("\033[30;47m");
            }
            else
            {
                prefix = "  ";
                System.out.print("\033[37;40m");
            }

            System.out.println(prefix + " << " + currentOption + " >>");

            //Console.WriteLine($"{prefix} << {currentOption} >>");
        }
        System.out.print("\033[0m");
    }

    @Override
    public int Run() throws IOException {
        return Run(null);
    }

    @Override
    public int Run(String message) throws IOException {
        Console console = System.console();
        if (console == null)
        {
            throw new IOException("No console available");
        }

        while(true)
        {
            if (message != null)
            {
                System.out.println(message);
            }
            DisplayOptions();

            int key=console.reader().read();

            switch(key)
            {
                case 'A':
                    selectedIndex--;
                    if(selectedIndex==-1)
                    {
                        selectedIndex=options.length-1;
                    }
                    break;

                case 'B':
                    selectedIndex++;
                    if(selectedIndex==options.length)
                    {
                        selectedIndex=0;
                    }
                    break;

                case '\r':
                    return selectedIndex;
            }
        }
    }
}
