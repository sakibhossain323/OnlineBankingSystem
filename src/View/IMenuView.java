package View;

import java.io.IOException;

public interface IMenuView
{
    void DisplayOptions();

    int Run() throws IOException;

    int Run(String message) throws IOException;
}
