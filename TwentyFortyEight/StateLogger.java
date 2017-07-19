package TwentyFortyEight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by james on 7/18/17.
 */
public class StateLogger {

    private final String directory = "/home/james/IdeaProjects/TwentyFortyEight/out/Logs";
    private final int logID;
    private final File log_file;

    public StateLogger() {
        logID = generatelogID();
        log_file = new File(getFileName());

        try {
            log_file.createNewFile();
        } catch (IOException e){
            System.out.println("Could Not Create Log File");
        }
    }
    public int generatelogID(){
        File file = new File(directory);
        int n = file.listFiles().length +1 ;
        return n;
    }

    private String getFileName(){
        StringBuilder sb = new StringBuilder();
        sb.append(directory + "/" + String.valueOf(logID));
        return sb.toString();
    }

    public void writeState(State state) throws IOException{
        FileWriter fileWriter = new FileWriter(log_file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(state.toCsvString() + "\n");
        bufferedWriter.close();

    }


}



