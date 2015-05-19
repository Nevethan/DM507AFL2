package dm507afl2;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 *
 * @author Eger
 */
public class ByteStream implements Iterable<Integer>, Closeable{
    // Underlying byte stream to read from.
    private InputStream input;

    // Buffer of up to 8 bits from the most recently read byte of the
    // underlying byte input stream. Is an int in the range 0 to 255
    // if bits are available, or is -1 if the end of stream is
    // reached.
    private int nextBits;


    // Creates a bit input stream based on the given byte input stream.
    public ByteStream(InputStream in) {
        if (in == null)
            throw new NullPointerException("No input stream given");
        input = in;
        try {
            nextBits = input.read();
        } catch (IOException e) {
            nextBits = -1;
        }
    }



    // Closes this stream and the underlying InputStream.
    public void close() throws IOException {
        input.close();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>()  {
            @Override
            public boolean hasNext() {
                return nextBits!=-1;
            }

            @Override
            public Integer next(){
                int nextBits1 = ByteStream.this.nextBits;
                try {
                    nextBits = input.read();
                } catch (IOException e) {
                    nextBits = -1;
                }
                return nextBits1;
            }
        };
    }
}