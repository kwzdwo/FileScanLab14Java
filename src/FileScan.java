import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileScan {
    public static void main(String[] args) {

        File selectedFile;
        String rec = "";

        if (args.length > 0) {
            System.out.println("The command line arguments are:");
            for (String val : args)
                System.out.println(val);

            try {


                File workingDirectory = new File(System.getProperty("user.dir"));
                Path path = Paths.get(args[0]);


                InputStream in = new BufferedInputStream(Files.newInputStream(path, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int line = 0;
                int words = 0;
                int characters = 0;
                String dataElements[];
                while (reader.ready()) {
                    rec = reader.readLine();
                    characters = characters + rec.length();
                    dataElements = rec.split("\\W");
                    words = words + dataElements.length;
                    line++;

                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }
                reader.close();
                System.out.println("\n\nData file read with command line args!\n");

                System.out.println("Summary");
                System.out.println(path.getFileName());
                System.out.println("Lines: " + line);
                System.out.println("Words: " + words);
                System.out.println("Characters: " + characters);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File not found.");
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        else {

            JFileChooser chooser = new JFileChooser();

            try {

                File workingDirectory = new File(System.getProperty("user.dir"));

                chooser.setCurrentDirectory(workingDirectory);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile();
                    Path file = selectedFile.toPath();

                    InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    int line = 0;
                    int words = 0;
                    int characters = 0;
                    String dataElements[];
                    while (reader.ready()) {
                        rec = reader.readLine();
                        characters = characters + rec.length();
                        dataElements = rec.split("\\W");
                        words = words + dataElements.length;
                        line++;

                        System.out.printf("\nLine %4d %-60s ", line, rec);
                    }
                    reader.close();
                    System.out.println("\n\nData file read with JFileChooser!\n");
                    System.out.println("Summary");
                    System.out.println(selectedFile.getName());
                    System.out.println("Lines: " + line);
                    System.out.println("Words: " + words);
                    System.out.println("Characters: " + characters);


                } else
                {
                    System.out.println("No file selected! ... exiting.\nRun the program again and select a file.");
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File not found.");
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }

}