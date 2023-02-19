
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
//to write  objects in append mode because in everycase runtime program writes a header in file but its reads only one header in file and skip multiple appended object data so create your objectoutput stream without header overrided method
public class AppendObjectOutputStream extends ObjectOutputStream {
    public AppendObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {

    }
}
