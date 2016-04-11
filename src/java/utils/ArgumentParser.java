package utils;

import java.io.File;

public class ArgumentParser
{
    private File f;

    public ArgumentParser(String[] args)
    {

        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals("-i") && (i + 1 < args.length))
            {
                f = new File(args[i + 1]);
                System.out.println("Processing file: " + f.getAbsolutePath());
                return;
            }
        }
        System.out.println("File not found, please check path.");
        System.exit(0);
    }

    public File getFile(){
        return f;
    }
}
