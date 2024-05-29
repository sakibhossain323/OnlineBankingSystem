import Repository.DbContext;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException
    {
        App app = new App(new DbContext());
        app.launch();
    }
}